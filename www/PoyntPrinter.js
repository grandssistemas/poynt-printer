var PoyntPrinter = {
	print: function (printId, image, successCallback, errorCallback){
		cordova.exec(successCallback,
			errorCallback,
			'PoyntPrinter',
			'print',
			[{
				"printId": printId,
				"image": image.replace('data:image/png;base64,', '')
			}]);
	}
};

module.exports = PoyntPrinter;
