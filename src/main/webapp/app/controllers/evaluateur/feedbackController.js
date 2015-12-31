var app = angular.module("lambda.bilan");

app.controller("feedbackController",
    ['$scope', '$filter','security', 'HTTP_METHOD','properties', 'utils','dao',
        function ($scope, $filter ,security, HTTP_METHOD, properties , utils,dao ) {

            $scope.themes=[];
            var task = dao.getData(properties.urlTheme,$scope.headerBasic, HTTP_METHOD.get);
            //on attent la reponse...
            task.promise.then(function (result) {
                // fin d'attente
                // erreur ?
                if (result.err == 0) {
                    //Pas d'erreur
                    $scope.themes = result.data;
                } else {
                    // il y a eu des erreurs
                    $scope.errors = {
                        title: properties.recupererThemeError,
                        messages: utils.getErrors(result),
                        show: true
                    };
                }
            });

            $scope.qualifications=[];
            var task = dao.getData(properties.urlQualification,$scope.headerBasic, HTTP_METHOD.get);
            //on attent la reponse...
            task.promise.then(function (result) {
                // fin d'attente
                // erreur ?
                if (result.err == 0) {
                    //Pas d'erreur
                    $scope.qualifications = result.data;
                } else {
                    // il y a eu des erreurs
                    $scope.errors = {
                        title: properties.recupererQualifError,
                        messages: utils.getErrors(result),
                        show: true
                    };
                }
            });

            var url;
            $scope.intervention = {};
            $scope.collaborateur={};
            $scope.action.creerFeedback=function(collaborateur)
            {
                    $scope.collaborateur=collaborateur;

                url = properties.urlIntervention + "/" + $scope.projet.idProjet + "/" +$scope.collaborateur.idUtilisateur;
                var task = dao.getData(url, $scope.headerBasic, HTTP_METHOD.get);
                //on attent la reponse...
                task.promise.then(function (result) {
                    // fin d'attente
                    // erreur ?
                    if (result.err == 0) {
                        //Pas d'erreur
                        $scope.intervention = result.data;
                        if($scope.intervention.notes.length==0){
                            for(var i=0;i<$scope.themes.length;i++){
                                $scope.intervention.notes.push({theme:$scope.themes[i],qualification:{},
                                                                    intervention:{idIntervention:$scope.intervention.idIntervention}});
                            }

                        }
                        else{
                            for(var i=0;i<$scope.themes.length;i++){
                                $scope.intervention.notes[i].intervention={idIntervention:$scope.intervention.idIntervention};
                            }

                        }


                    } else {
                        // il y a eu des erreurs
                        $scope.errors = {
                            title: properties.recupererInterventionError,
                            messages: utils.getErrors(result),
                            show: true
                        };
                    }
                });
            }

            $scope.enregitrerFeedback=function(){
                var task = dao.getData(properties.urlFeedback,$scope.headerBasic, HTTP_METHOD.post,$scope.intervention.notes);
                //on attent la reponse...
                task.promise.then(function (result) {
                    // fin d'attente
                    // erreur ?
                    if (result.err == 0) {
                        $scope.errors.show = false;
                        $scope.succes.show=true;
                        $scope.succes.message=result.data;
                        $scope.intervention={};
                    } else {
                        // il y a eu des erreurs
                        $scope.succes.show=false;
                        $scope.errors.show = true;
                        $scope.errors.title = properties.enregistrerFeedbackError;
                        $scope.errors.messages = utils.getErrors(result);
                    }
                });

            };

            $scope.validerFeedback=function(){
                var task = dao.getData(properties.urlFeedback+"/"+$scope.intervention.idIntervention,
                    $scope.headerBasic, HTTP_METHOD.put,$scope.intervention.notes);
                //on attent la reponse...
                task.promise.then(function (result) {
                    // fin d'attente
                    // erreur ?
                    if (result.err == 0) {
                        $scope.errors.show = false;
                        $scope.succes.show=true;
                        $scope.succes.message=result.data;
                    } else {
                        // il y a eu des erreurs
                        $scope.succes.show=false;
                        $scope.errors.show = true;
                        $scope.errors.title = properties.validerFeedbackError;
                        $scope.errors.messages = utils.getErrors(result);
                    }
                });

            };




            var mois;
            //recuperation date serveur
            var task = dao.getData("/date_serveur", $scope.headerBasic, HTTP_METHOD.get);
            //on attent la reponse...
            task.promise.then(function (result) {
                // fin d'attente
                // erreur ?
                if (result.err == 0) {
                    //Pas d'erreur
                    var date = new Date(result.data);
                    mois = date.getMonth()+1;
                } else {
                    $scope.succes.show=false;
                    $scope.errors = {
                        title: properties.recupererDateError,
                        messages: utils.getErrors(result),
                        show: true
                    };
                }
            });

            $scope.validerShow=function(){
                console.log("---------   "+angular.toJson($scope.collaborateur));
                var date = new Date($scope.collaborateur.dateEmbaucheCollaborateur);
                var moisEmbauch = date.getMonth()+1;
                if(mois==moisEmbauch)
                    return true;
                return false;
            };



        }])
;

