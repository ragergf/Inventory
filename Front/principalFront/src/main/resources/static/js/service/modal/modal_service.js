'use strict';

App.factory('ModalService', [ function(){

	var showButtonOk = false;
	return {
		changeShowButtonOk:  function(status)
		{
			showButtonOk = status;
		},
		getShowButtonOk: function()
		{
			return showButtonOk;
		}
	};
}]);