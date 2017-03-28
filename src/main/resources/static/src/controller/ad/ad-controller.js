angular.module('adExtreme')
.controller('AdCtrl', function ($scope, Advertisement, $uibModal) {
    $scope.ads = [{title : 'Casa',
        role : 'Imóvel',
        postDate : '12-mar-2017 12:16:08',
        price : 	'R$ 22,000.22',
        owner : 'Sávio Muniz'},
        {title : 'Emprego de caleireiro',
            role : 'Emprego',
            postDate : '14-mar-2017 20:26:32',
            price : 'R$ 880.00',
            owner : 'Nicácio Oliveira'
        }];

    $scope.loadAds = function() {
        Advertisement.getAll().
        then (function success (ads) {
            $scope.ads = ads.data;
        }, function error (error) {
            $defaultError();
        });
    }

    $scope.openAdModal = function(index) {
        $scope.currentAd = $scope.ads[index];
        var parent = this;

        var modalInstance = $uibModal.open({
          animation: true,
          ariaLabelledBy: 'modal-title',
          ariaDescribedBy: 'modal-body',
          templateUrl: '/templates/directives/ad/ad-modal.html',
          controller: 'AdModalCtrl',
          scope: this,
          controllerAs: '$ctrl'
        });

        modalInstance.result.then(function (confirmationMail) {

        });
    }


    $scope.isEmpty = function () {
        if ($scope.ads.length == 0) return true;
        else return false;
    }

    $scope.clear = function () {
        $scope.ads = [];
        $scope.loggedIn = true;
    }
});

