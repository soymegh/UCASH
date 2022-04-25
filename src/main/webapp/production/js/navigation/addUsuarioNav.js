var actions = [

	{
		"key": 49,
		"type": "input",
		"id": "txt_nombre",
	},
	{
		"key": 50,
		"type": "input",
		"id": "txt_apellido"
	},
	{
		"key": 51,
		"type": "input",
		"id": "txt_email"
	},
	{
		"key": 52,
		"type": "input",
		"id": "email2"
	},
	{
		"key": 53,
		"type": "input",
		"id": "txt_usuario"
	},
	{
		"key": 54,
		"type": "input",
		"id": "txt_password"
	},
	{
		"key": 55,
		"type": "input",
		"id": "txtclave2"
	},
	{
		"key": 8,
		"type": "button",
		"id": "button_cancel"
	},
	{
		"key": 13,
		"type": "button",
		"id": "button_execute"
	}
];


document.addEventListener('keydown', e => {
	for (var x = 0; x < actions.length; x++) {
		if (e.keyCode === actions[x].key) {
			redirect(x);
		};
	};
});


function redirect(index) {
	if (actions[index].type == "input") {
		var input = document.getElementById(actions[index].id).focus();
	} else if (actions[index].type == "button") {
		var input = document.getElementById(actions[index].id).onsubmit()
	};
};



