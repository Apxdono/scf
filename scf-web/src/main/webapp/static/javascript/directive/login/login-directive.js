var _t = require('tools');

module.exports = /*@ngInject*/ LoginDirective;

function LoginDirective($log, UserService) {
    return {
        restrict: 'AC',
        scope: {
            lbTarget: '@'
        },
        controller : /*@ngInject*/ function($scope){
            $scope.login = function(){
                UserService.login($scope.username,$scope.password);
            };
        },
        templateUrl : './static/components/login/login-box.html',
        link: function (scope, element, attributes) {
            var loginBox = element;
            var siteContent = _t.ng.element(scope.lbTarget);
            checkUser(UserService.user(),loginBox,siteContent);
            UserService.subscribe('user', scope, function(user){checkUser(user,loginBox,siteContent);});
        }
    };
}


function checkUser(user, loginBox, siteContent) {
    if (!user) {
        loginBox.show();
        siteContent.hide();
    } else {
        loginBox.hide();
        siteContent.show();
    }
}