var app = angular.module( 'xstore', [
  'auth0',
  'ngRoute',
  'ngResource'
]);



app.config( function myAppConfig ( $routeProvider, authProvider, $httpProvider, $locationProvider) {
  $routeProvider
    .when( '/', {
      controller: 'ProductListCtrl',
      templateUrl: 'Product/list.scala.html',
      pageTitle: 'All Products',
      requiresLogin: true
    })

    .when( '/product/crt/', {
      controller: 'ProductCreateCtrl',
      templateUrl: 'Product/create.scala.html',
      pageTitle: 'Create Product',
      requiresLogin: true
    })

    .when( '/product/show/:id', {
      controller: 'ProductShowCtrl',
      templateUrl: 'Product/show.scala.html',
      pageTitle: 'Product Details',
      requiresLogin: true
    })
	
    .when( '/product/list', {
      controller: 'ProductListCtrl',
      templateUrl: 'Product/list.scala.html',
      pageTitle: 'Product Details',
      requiresLogin: true
    })
	
    .when( '/login', {
      templateUrl: 'Application/login.scala.html',
      pageTitle: 'Login'
    });


  authProvider.init({
    domain: "boxed.auth0.com",
    clientID: "rectojRIfjERbByPK2AdO7EHf9ywZt3U",
    callbackURL: location.href,
    loginUrl: '/login'
  });

  authProvider.on('loginSuccess', function($location) {
    $location.path('/');
  });

  authProvider.on('loginFailure', function($log, error) {
    $log('Error logging in', error);
  });

  $httpProvider.interceptors.push('authInterceptor');
})
.run(function(auth) {
  auth.hookEvents();
});



app.controller( 'AppCtrl', ['$scope', '$location', function ( $scope, $location ) {

  $scope.$on('$routeChangeSuccess', function(e, nextRoute){
  
    if ( nextRoute.$$route && angular.isDefined( nextRoute.$$route.pageTitle ) ) {
	
      $scope.pageTitle = nextRoute.$$route.pageTitle;
    }
  });
}]);


app.controller( 'RtCtrl', [ '$scope', '$location', 'auth', function ( $scope, $location, auth ) {
	
	$scope.auth = auth;
	
	$scope.login = function () {
		console.log("login");
		auth.signin({
		  // popup: true to use popup instead of redirect
		});		
	}
	
	$scope.logout = function () {
		console.log("logout");
		auth.signout();
		$location.path('/login');
	}

}]);


app.controller( 'ProductListCtrl', [ '$scope', '$location', '$resource', function ( $scope, $location, $resource ) {

    var rr = $resource('/product/list', {}, {
      list: {method:'GET', params:{}, isArray:true}
    });

	$scope.products = rr.list();
	
//	$scope.user_name = (auth.profile === null) ? "" : auth.profile.name;	
}]);


app.controller( 'ProductCreateCtrl', [ '$scope', '$location', '$resource', function ( $scope, $location, $resource ) {

	$scope.prod = {id: 0, name: "", description: ""};

	$scope.create = function(prod) {
		var rr = $resource('/product/create', {}, {
		  create: {method:'POST', params:{name: $scope.prod.name, description: $scope.prod.description}, isArray:false}
		});

		rr.create();
		
		$scope.prod = {id: 0, name: "", description: ""};
	}

//	$scope.user_name = (auth.profile === null) ? "" : auth.profile.name;
}]);

app.controller( 'ProductShowCtrl', [ '$scope', '$location', '$resource', '$routeParams', function ( $scope, $location, $resource, $routeParams ) {

    var rr = $resource('/product/show/:id', {}, {
      get: {method:'GET', params:{id: $routeParams.id}, isArray:false}
    });

	$scope.prod = rr.get();

//	$scope.user_name = (auth.profile === null) ? "" : auth.profile.name;
}]);