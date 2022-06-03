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

var input, signal = true; 

window.addEventListener('DOMContentLoaded', e => {
	/*Obteniendo input de filtrado para asignarle un id y poder manipularlo*/
	var parentNode = document.getElementById("datatable-buttons_filter");
	parentNode.firstElementChild.firstElementChild.setAttribute("id", "inputFilter");
	
	input = document.getElementById("inputFilter");
	
	/*Verificando si el input esta activo */
	input.addEventListener('focus', () => {
		signal = false; 
	});
	/*Verificando si el input esta no activo */
	input.addEventListener('focusout', () => {
		signal = true; 
	});
	
	/*Si el input no esta activo y precionamos una tecla de navegación, ¡Naveguemos!*/
	document.addEventListener('keydown', e => {
		if (signal) {
			for (var x = 0; x < actions.length; x++) {
				if (e.keyCode === actions[x].key) {
					redirect(x)
				}
			};
		};
	});
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