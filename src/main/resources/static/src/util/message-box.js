"use strict"

function $messageBox(title, message, size, headerClass, buttons) {
    var config = {};

    if (size) {
        config.size = size;
    }
    if (title) {
        config.title = title;
    }
    if (message) {
        config.message = message;
    }
    if (buttons) {
        config.buttons = buttons;
    }

    bootbox.hideAll();

    var box = bootbox.dialog(config);
    if (headerClass) {
        box.find(".modal-header").addClass('modal-header-' + headerClass);
    }
    return box;
}

function $successBox (title, message, callback) {
	var box = $messageBox(title, message, "small", 'success',
						  {
						  	ok: {
						  		label : 'OK',
						  		className : 'btn-success',
						  		callback : callback || function () {}
						  	}
						  }
						  );
	return box;
}

function $infoBox (title, message, callback) {
	var box = $messageBox(title, message, "small", 'info',
						  {
						  	ok: {
						  		label : 'OK',
						  		className : 'btn-success',
						  		callback : callback || function () {}
						  	}
						  }
						  );
	return box;
}

function $warningBox (title, message, confirm, cancel) {
	var box = $messageBox(title, message, "small", 'warning',
						  {
						  	ok: {
						  		label : 'OK',
						  		className : 'btn-success',
						  		callback : confirm || function () {}
						  	},
						  	cancel: {
						  		label : 'Cancel',
						  		className : 'btn-danger',
						  		callback : cancel || function () {}
						  	}
						  }
						  );
	return box;
}