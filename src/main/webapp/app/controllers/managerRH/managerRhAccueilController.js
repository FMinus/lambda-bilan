var app = angular.module("lambda.bilan", ["ngCookies","isteven-multi-select"]);



app.controller("managerRhAccueilController",
    ['$scope', '$filter','security', 'HTTP_METHOD','properties', 'utils','dao',
        function ($scope, $filter ,security, HTTP_METHOD, properties , utils,dao ) {

            var user=$scope.user=security.checkSecurity(["ManagerRH"]);
            $scope.headerBasic=user.headerBasic;
            var idManagerRH=user.idUtilisateur;

            $scope.errors={show:false,messages:[]};
            $scope.succes={show:false,message:''};
            $scope.collabsSansProjet=[];
            var listerCollabSansProjet = function(){
                //get les collabs sans projet
                var task = dao.getData(properties.urlCollabsSansProjet, $scope.headerBasic, HTTP_METHOD.get);
                //on attent la reponse...
                task.promise.then(function (result) {
                    // fin d'attente
                    // erreur ?
                    if (result.err == 0) {
                        //Pas d'erreur
                        $scope.collabsSansProjet = result.data;
                    } else {
                        // il y a eu des erreurs
                        $scope.succes.show=false;
                        $scope.errors = {
                            title: properties.listerCollabsSansProjetError,
                            messages: utils.getErrors(result),
                            show: true
                        };
                    }
                });
            };
            listerCollabSansProjet();

            $scope.collabsSansObj=[];
            var listerCollabSansObj=function(){
                //get les collabs sans objectifs
                var task = dao.getData(properties.urlCollabsSansObj, $scope.headerBasic, HTTP_METHOD.get);
                //on attent la reponse...
                task.promise.then(function (result) {
                    // fin d'attente
                    // erreur ?
                    if (result.err == 0) {
                        //Pas d'erreur
                        $scope.collabsSansObj = result.data;
                    } else {
                        // il y a eu des erreurs
                        $scope.succes.show=false;
                        $scope.errors = {

                            title: properties.listerCollabsSansObjError,
                            messages: utils.getErrors(result),
                            show: true
                        };
                    }
                });
            };
            listerCollabSansObj();

            $scope.objectifsRef=[];
            var listerObjectif=function(){
                    //get les objectifs refusés
                    var task = dao.getData(properties.urlObjRefus+"/"+idManagerRH, $scope.headerBasic, HTTP_METHOD.get);
                    //on attent la reponse...
                    task.promise.then(function (result) {
                        // fin d'attente
                        // erreur ?
                        if (result.err == 0) {
                            //Pas d'erreur
                            $scope.objectifsRef = result.data;
                        } else {
                            // il y a eu des erreurs
                            $scope.succes.show=false;
                            $scope.errors = {

                                title: properties.listerObjRefusError,
                                messages: utils.getErrors(result),
                                show: true
                            };
                        }
                    });
            };
            listerObjectif();


            $scope.categories=[];
            var task = dao.getData(properties.urlCategorie, $scope.headerBasic, HTTP_METHOD.get);
            //on attent la reponse...
            task.promise.then(function (result) {
                // fin d'attente
                // erreur ?
                if (result.err == 0) {
                    //Pas d'erreurs
                    $scope.categories=result.data;
                } else {
                    // il y a eu des erreurs
                    $scope.succes.show=false;
                    $scope.errors = {
                        title: properties.recuperationsCategoriesError,
                        messages: utils.getErrors(result),
                        show: true
                    };
                }
            });


            $scope.supprimerObjectif=function(id){
                var task = dao.getData(properties.urlObjectif+"/"+id, $scope.headerBasic, HTTP_METHOD.delete);
                //on attent la reponse...
                task.promise.then(function (result) {
                    // fin d'attente
                    // erreur ?
                    if (result.err == 0) {
                        $scope.errors.show=false;
                        $scope.succes={show:true,message:result.data};
                        listerObjectif();
                    } else {
                        // il y a eu des erreurs
                        $scope.succes.show=false;
                        $scope.errors = {
                            title: properties.supprimerObjectifError,
                            messages: utils.getErrors(result),
                            show: true
                        };
                    }
                });

            };

            $scope.validerObjectif=function(id){
                var task = dao.getData(properties.urlValiderObjectif+"/"+id, $scope.headerBasic, HTTP_METHOD.put,{});
                //on attent la reponse...
                task.promise.then(function (result) {
                    // fin d'attente
                    // erreur ?
                    if (result.err == 0) {
                        //Pas d'erreurs
                        $scope.errors.show=false;
                        $scope.succes={show:true,message:result.data};
                        listerObjectif();
                    } else {
                        // il y a eu des erreurs
                        $scope.succes.show=false;
                        $scope.errors = {

                            title: properties.validerObjectifError,
                            messages: utils.getErrors(result),
                            show: true
                        };
                    }
                });

            };

            $scope.action={
                affecterProjet:undefined,
                ajouterObjectif:undefined,
                modifierObjectif:undefined,
                listerCollabSansObj:listerCollabSansObj,
                listerCollabSansProjet:listerCollabSansProjet,
                listerObjectif:listerObjectif
            };







        }])
;

