var app = angular.module("lambda.bilan", ["ngCookies","ngTable","isteven-multi-select"]);

app.controller("listCollaborateurController",
    ['$scope','$cookies','$http','ngTableParams', '$filter','security', 'HTTP_METHOD','properties', 'utils','dao',
        function ($scope, $cookies,$http,NgTableParams, $filter ,security, HTTP_METHOD, properties , utils,dao ) {

            var user=$scope.user=security.checkSecurity(["ManagerRH"]);
            $scope.headerBasic=user.headerBasic;

            var idManagerRH=user.idUtilisateur;
            $scope.errors={show:false,messages:[]};
            $scope.succes={show:false,message:''};
            $scope.tabledata={table:[]};
            $scope.tableParams = new NgTableParams({
                page: 1,            // show first page
                count: 10,          // count per page
                sorting: {
                    nomUtilisateur: 'asc'     // initial sorting
                },
                filter: {
                    nomUtilisateur: ''       // initial filter
                }
            },{
                total: $scope.tabledata.table.length, // length of data
                getData: function($defer, params) {
                    // use build-in angular filter
                    var filteredData = params.filter() ?
                        $filter('filter')($scope.tabledata.table, params.filter()) :
                        $scope.tabledata.table;

                    var orderedData = params.sorting() ?
                        $filter('orderBy')(filteredData, params.orderBy()) : filteredData;

                    params.total(filteredData.length);

                    $defer.resolve(orderedData.slice((params.page() - 1) * params.count(), params.page() * params.count()));
                }
            });


            //chargement liste collabs
            var task = dao.getData("/managerRHs/"+idManagerRH+"/collaborateurs", $scope.headerBasic, HTTP_METHOD.get);
            //on attent la reponse...
            task.promise.then(function (result) {
                // fin d'attente
                // erreur ?
                if (result.err == 0) {
                    //Pas d'erreur
                    $scope.tabledata.table = result.data;
                    $scope.tableParams.reload();
                } else {
                    $scope.succes.show=false;
                    $scope.errors = {
                        title: properties.listerCollabError,
                        messages: utils.getErrors(result),
                        show: true
                    };
                }
            });

            $scope.evaluerObjectifs=function(collaborateur){
                $cookies.putObject("collaborateur",collaborateur);

            };
            $scope.afficherBAPs=function(collaborateur){
                $cookies.putObject("collaborateur",collaborateur);
            };

            $scope.action={affecterProjet:undefined,dresserPlanAmelioration:undefined};



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

            $scope.bipShow=function(dateEmbauche){

                var date = new Date(dateEmbauche);
                var moisEmbauch = date.getMonth()+1;
                if(mois==moisEmbauch+6)
                    return true;
                return false;
            };

            $scope.bapShow=function(dateEmbauche){
                var date = new Date(dateEmbauche);
                var moisEmbauch = date.getMonth()+1;
                if(mois==moisEmbauch)
                    return true;
                return false;
            };

        }])
;


