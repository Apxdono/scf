module.exports = /*@ngInject*/ MainController;
function MainController($scope,$log,UserService){
    $scope.greeting = 'Hello oleg!';
    $scope.userSvc = UserService;
    UserService.subscribe('user',$scope,'user');
    $scope.login = function(){
        UserService.login($scope.username,$scope.password);
    };
}
