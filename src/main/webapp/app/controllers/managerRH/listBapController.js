/**
 * Created by ST on 03/05/2014.
 */
var app = angular.module("lambda.bilan", ["ngCookies","ngTable"]);

app.controller("listBapController",
    ['$scope','$cookies','$http','ngTableParams', '$filter','security', 'HTTP_METHOD','properties', 'utils','dao',
        function ($scope,$cookies,$http,NgTableParams, $filter ,security, HTTP_METHOD, properties , utils,dao ) {

            var user=security.checkSecurity("ManagerRH","Administrateur","Collaborateur");
            $scope.user=user;
            $scope.succes={show:false,message:''};
            $scope.errors={show:false,messages:[]};

            $scope.collaborateur = $cookies.getObject("collaborateur");
            var idCollaborateur = $scope.collaborateur.idUtilisateur;

            $scope.tabledata={table:[]};
            $scope.tableParams = new NgTableParams({
                page: 1,            // show first page
                count: 10,          // count per page
                sorting: {
                    dateBAP: 'desc'     // initial sorting
                },
                filter: {
                    dateBAP: ''       // initial filter
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


            //affichage des lists des baps du collabrateurs
            var task = dao.getData("/collaborateurs/"+idCollaborateur+"/baps", $scope.headerBasic, HTTP_METHOD.get);
            //on attent la reponse...
            task.promise.then(function (result) {
                // fin d'attente
                // erreur ?
                if (result.err == 0) {
                    //Pas d'erreur
                    $scope.tabledata.table = result.data;
                    $scope.tableParams.reload();
                } else {
                    $scope.errors = {

                        title: properties.listerBapError,
                        messages: utils.getErrors(result),
                        show: true
                    };
                }


            });

            $scope.fiche=[];
            $scope.consulterFiche=function(year){
            	$scope.year=year;

                var task = dao.getData("/collaborateurs/"+idCollaborateur+"/ficheObjectifs?year="+year,$scope.headerBasic, HTTP_METHOD.get);
                //on attent la reponse...
                task.promise.then(function (result) {
                    // fin d'attente
                    // erreur ?
                    if (result.err == 0) {
                        //Pas d'erreur
                       $scope.fiche=result.data;
                    } else {
                        $scope.errors = {
                            title: properties.listerBapError,
                            messages: utils.getErrors(result),
                            show: true
                        };
                    }


                });
            };

            $scope.plan=[];
            $scope.consulterPlan=function(year){

                var task = dao.getData("/collaborateurs/"+idCollaborateur+"/planAmeliorations?year="+year,$scope.headerBasic, HTTP_METHOD.get);
                //on attent la reponse...
                task.promise.then(function (result) {
                    // fin d'attente
                    // erreur ?
                    if (result.err == 0) {
                        //Pas d'erreur
                        $scope.plan=result.data;
                    } else {
                        $scope.errors = {
                            title: properties.listerBapError,
                            messages: utils.getErrors(result),
                            show: true
                        };
                    }


                });
            };

            $scope.feedbacks=[];
            $scope.year;
            $scope.setFeedbacks=function(year){
                $scope.year=year;
                var task = dao.getData("/collaborateurs/"+2+"/feedBacks/?year="+year,$scope.headerBasic, HTTP_METHOD.get);
                //on attent la reponse...
                task.promise.then(function (result) {
                    // fin d'attente
                    // erreur ?
                    if (result.err == 0) {
                        //Pas d'erreur
                        $scope.feedbacks=result.data;
                    } else {
                        $scope.errors = {
                            title: properties.listerFeedbackError,
                            messages: utils.getErrors(result),
                            show: true
                        };
                    }
                });

            };

            $scope.feedback={};
            $scope.setFeedback=function(feedback){
                $scope.feedback=feedback;

            };



        }])
;

