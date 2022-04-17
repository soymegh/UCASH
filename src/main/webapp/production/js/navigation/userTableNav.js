var actions = [
	{
		"key": 97,
		"route": "addUsuario.jsp",
		"parameter": false
	},
	{
		"key": 98,
		"route": "editUsuario.jsp?",
		"parameter": true,
		"query": "idUsuario="
	},
	{
		"key": 99,
		"route": "viewUsuario.jsp?",
		"parameter": true,
		"query": "idUsuario="
	},
	{
		"key": 96,
		"route": "deleteUsuario.jsp?",
		"parameter": true,
		"query": "idUsuario="
	}
];


document.addEventListener('keydown', e => {
	for (var x = 0; x < actions.length; x++) {
		if (e.keyCode === actions[x].key) {
			redirect(x)
		}
	};
});


function redirect(index) {
	switch (actions[index].parameter) {
		case true:
			var parameter = prompt("Digite el id del usuario que requiere:");
			location.href = `${actions[index].route}${actions[index].query}${parameter}`;
			break;
		case false:
			location.href = actions[index].route;
			break;
	}
};