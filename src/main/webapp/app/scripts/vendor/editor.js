'use strict';
angular.module('simditor',[]).directive('simditor', function ($window) {

    var Simditor = $window.Simditor;

    return {
      require: "?^ngModel",
      link: function (scope, element, attrs, ngModel) {
        var placeholder = attrs.placeholder;
        if(placeholder == undefined) {
            placeholder = "";
        }
        element.append("<div style='height:300px;' placeholder='" + placeholder + "'></div>");

        scope.simditor = new Simditor({
          textarea: element.children()[0],
          pasteImage: true,
          toolbar: ['title', 'bold', 'italic', 'underline', 'strikethrough', 'ol', 'ul', 'blockquote', 'link', 'hr', 'indent', 'outdent'],
          defaultImage: 'assets/images/image.png',
          upload: location.search === '?upload' ? {
            url: '/upload'
          } : false
        });

        function readViewText() {
          var html = element.find('.simditor-body').html();
          var text = element.find('.simditor-body').text();
          if (text === '') {
            html = '';
          }

          ngModel.$setViewValue(html);
        }

        var $target = element.find('.simditor-body');

        ngModel.$render = function () {
          scope.simditor.focus();
          $target.prepend(ngModel.$viewValue);
        };

        element.on('blur keyup change input', function () {
          scope.$apply(readViewText);
        });
      }
    };
});