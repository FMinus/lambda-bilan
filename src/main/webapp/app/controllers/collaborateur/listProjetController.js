var app = angular.module("lambda.bilan", ["ngCookies"]);

app.controller("listeProjetController",
    ['$scope','$cookies','$http', '$filter','security', 'HTTP_METHOD','properties', 'utils','dao',
        function ($scope, $cookies,$http, $filter ,security, HTTP_METHOD, properties , utils,dao ) {

            var user=$scope.user=security.checkSecurity(["Collaborateur"]);
            $scope.headerBasic=user.headerBasic;
            idCollaborateur=user.idUtilisateur;
            $scope.errors={show:false,messages:[]};
            $scope.succes={show:false,message:''};


            $scope.projets=[];
            //chargement liste objectifs
            var task = dao.getData("/collaborateurs/"+idCollaborateur+"/projets", $scope.headerBasic, HTTP_METHOD.get);
            //on attent la reponse...
            task.promise.then(function (result) {
                // fin d'attente
                // erreur ?
                if (result.err == 0) {
                    //Pas d'erreur
                    $scope.projets=result.data;
                } else {
                    $scope.succes.show=false;
                    $scope.errors = {
                        title: properties.listerProjetError,
                        messages: utils.getErrors(result),
                        show: true
                    };
                }
            });

        }])
;


