$.blockUI.defaults.css = {
	padding: 0,
    margin: 0,
    width: '30%',
    top: '40%',
    left: '35%',
    textAlign: 'center',
    cursor: 'wait'
};

function $mask() { 
	$.blockUI({ message: '<h1><img src="/style/imgs/spinner.gif"/> Carregando...</h1>' });
}

function $unmask() { 
	$.unblockUI();
}