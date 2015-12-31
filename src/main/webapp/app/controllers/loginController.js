var app = angular.module("lambda.bilan", ["ngCookies","base64"]);
app.controller("loginController",
    ['$scope','$cookies','$base64', '$filter','security', 'HTTP_METHOD','properties', 'utils','dao',
        function ($scope,$cookies,$base64, $filter ,security, HTTP_METHOD, properties , utils,dao ) {

            $cookies.remove("user");
            $scope.isIncorrecte=false;

            $scope.connecter=function(){

            var login=  {
                    "passwordUtilisateur": $scope.mdp,
                    "emailUtilisateur": $scope.email
                };
           
               
                var task = dao.getData("/login", null, HTTP_METHOD.post,login);
                //on attent la reponse...
                task.promise.then(function (result) {
                    // fin d'attente
                    // erreur ?
                    if (result.err == 0) {
                        //Pas d'erreur
                        var user = result.data;
                        user.headerBasic="Basic " + $base64.encode($scope.email + ":" + $scope.mdp);
                        security.setCurrentUser(user);
                        switch(user.role){
                            case  "Evaluateur":
                                utils.redirectTo("/evaluateur/evaluateur_liste_projet.html");
                                break;

                            case "Administrateur":
                                utils.redirectTo("/administrateur/gestion_utilisateur.html");
                                break;


                            case "ManagerRH":
                                utils.redirectTo("/managerRH/managerRH_accueil.html");
                                break;

                            case "Collaborateur":
                                utils.redirectTo("/collaborateur/accueil.html");
                                break;

                            default:
                                break;
                        }

                    } else {
                       $scope.isIncorrecte=true;
                    }



                });

            };

        }])
;

