
		function isDNI() {
			const inputs = document.getElementById("altaForm").elements;
			const inputByName = inputs["nifInput"];
			nif = inputByName.value;
			number = nif.substr(0, nif.length - 1);
			letter = nif.substr(nif.length - 1);

			if (!isNaN(number) && isNaN(letter)
					&& number.toString().length === 8 && letter.length === 1) {
				let lettersOrder = 'TRWAGMYFPDXBNJZSQVHLCKET';
				if (lettersOrder[number % 23] === letter.toUpperCase()) {
					return true;
				}
			}

			inputByName.value = "";
			alert("El NIF definido no es válido.");
			return false;
		}
		
		function filtrar(page) {
				const form = document.getElementById("filterForm");
		
				form.action= '/filter/'+page;
				form.method="POST";
				form.submit();
		
		}
		
		function setOficina() {
			if(document.getElementById("perfil").value=="bo") {
			const value = document.getElementById("oficina").value;
			
				if(value=="7300" || value=="7303" || value=="9348" || value=="9792") {
					document.getElementById("oficina").value="";
				}
			}
		}
		
		function validarCalculoEsb(){
		//botonCalculoNP
			const botonCalculoP = document.getElementById("botonCalculoP");
			const botonCalculoNP = document.getElementById("botonCalculoNP");
			
			//botonCalculoNP
			if(null!= botonCalculoNP) {
				//importeTotal
				const importeTotal = document.getElementById("importeTotal");
				if(importeTotal.value=="") {
					botonCalculoNP.disabled = true;
					document.getElementById("guardarCalculo").disabled = true;
					return;
				}
				//vencimientoNoPyme
				const vencimientoNoPyme = document.getElementById("vencimientoNoPyme");
				if(vencimientoNoPyme.value=="") {
					botonCalculoNP.disabled = true;
					document.getElementById("guardarCalculo").disabled = true;
					return;
				}
			
			botonCalculoNP.disabled = false;
			return;
			} //Fin /botonCalculoNP
			
			//botonCalculoP
			if(null!= botonCalculoP) {
				//importeTotal
				const importeTotal = document.getElementById("importeTotal");
				if(importeTotal.value=="") {
					botonCalculoP.disabled = true;
					document.getElementById("guardarCalculo").disabled = true;
					return;
				}
				
				//vencimientoPyme
				const vencimientoPyme = document.getElementById("vencimientoPyme");
				if(vencimientoPyme.value=="") {
					botonCalculoP.disabled = true;
					document.getElementById("guardarCalculo").disabled = true;
					return;
				}
		
				//carencia	
				const carencia = document.getElementById("carencia");
				if(carencia.value=="") {
					botonCalculoP.disabled = true;
					document.getElementById("guardarCalculo").disabled = true;
					return;
				}
				
				//referencia
				const referencia = document.getElementById("referencia");
				if(referencia.value=="") {
					botonCalculoP.disabled = true;
					document.getElementById("guardarCalculo").disabled = true;
					return;
				}
				
				//pagosPeriodo
				const pagosPeriodo = document.getElementById("pagosPeriodo");
				if(pagosPeriodo.value=="") {
					botonCalculoP.disabled = true;
					document.getElementById("guardarCalculo").disabled = true;
					return;
				}
				
				botonCalculoP.disabled = false;
				return;
		
			} //Fin /botonCalculoP
		}
		
		function validarGuardarEsb(){
			const guardarCalculo = document.getElementById("guardarCalculo");
		
			const esbPyme = document.getElementById("esbPyme");
			if(null!= esbPyme) {
				
				if(esbPyme.value!="") {
					guardarCalculo.disabled = false;
				} else {
					guardarCalculo.disabled = true;
				}
			} //Guardar ESB PYME
			
		
			const esbNoPymeCalculado = document.getElementById("esbNoPymeCalculado");
			if(null!= esbNoPymeCalculado) {
				
				if(esbNoPymeCalculado.value!="") {
					guardarCalculo.disabled = false;
				} else {
					guardarCalculo.disabled = true;
				}
			} //Guardar ESB NO PYME
		}
		
		function limpiarBusqueda() {
				
				document.getElementById("numeroSIA").value="";
				document.getElementById("cif").value="";
				
				if(document.getElementById("perfil").value=="bo") {
					document.getElementById("oficina").value="";
				}
				
				document.getElementById("fechaDesde").value="";
				document.getElementById("fechaHasta").value="";
				document.getElementById("estado").value="";
		}
		
		function validaNumeroSia() {
			const sia2 = document.getElementById("numeroSiaInput").value;
			sia = sia2.replace(" ","").replace(" ","");
			if(isNaN(sia) ){
				document.getElementById("numeroSiaInput").value = "";
				alert("El SIA no es válido.");
			} else{
				const newsia = sia.substring(0,4) + " " + sia.substring(4,6) + " " + sia.substring(6);
	  			document.getElementById("numeroSiaInput").value = newsia;
	  			}
		}
		
		
		function formateaNumero(valor){
			const value1 = valor.replaceAll(".","");
			const value2 = value1.replace(",",".");
			const value3 = value2.replace(" ","");
			return value3.replace("€","");
			
		}
		
		function formateaDecimal(valor){
			const value1 = valor.replaceAll(",",".");
			return value1.replace(" ","");
		}
		
		function checkCampoDecimal(field) {
			const campo = document.getElementById(field);
			var valor = formateaDecimal(campo.value); 		
					
			if (isNaN(valor)) {
				campo.value = "";
				alert("El valor no es válido.");
			} else {
				document.getElementById(field).value= valor.replace(".",",");
			}
		}
		
		
		function checkCampoMoneda(field) {
			const inputs = document.getElementById("altaForm").elements;
			const inputByName = inputs[field];
			const format = formateaNumero(inputByName.value);
			
			if (isNaN(format)) {
				inputByName.value = "";
				alert("El importe no es válido.");
			} else {
				const numero = new Number(format);
				const result = numero.toLocaleString('es-ES', { style: 'currency', currency: 'EUR' });
				
				inputByName.value = result;
			}
				
		}
		
		function validCpMx(cp) {

			if (cp.length > 4 && /^\d{5}$/.test(cp) && 12345 != cp
					&& !/0{5}/.test(cp) && !/1{5}/.test(cp) && !/2{5}/.test(cp)
					&& !/3{5}/.test(cp) && !/4{5}/.test(cp) && !/5{5}/.test(cp)
					&& !/6{5}/.test(cp) && !/7{5}/.test(cp) && !/8{5}/.test(cp)
					&& !/9{5}/.test(cp)) {
				return true;
			} else {
				return false;
			}
		}

		function cpAndalucia(cp) {
			//Almeria 04
			//Cadiz 11
			//Cordoba 14
			//Granada 18
			//Huelva 21
			//Jaen 23
			//Malaga 29
			//Sevilla 41

			if (cp.startsWith("04") || cp.startsWith("11")
					|| cp.startsWith("14") || cp.startsWith("18")
					|| cp.startsWith("21") || cp.startsWith("23")
					|| cp.startsWith("29") || cp.startsWith("41")) {
				return true;
			}

			return false;
		}
		
		function redefineSegmento() {
			var test = "false";
			if(validaFacturacionYBalance()) {
				test = "true";
			}
			
			if(test=="false") {
				if(validaPorcentaje()) {
					test = "true";
				}
			}
			
			if(test=="false") {
				if(validaNoSuperaTope('numEmplUltimoAnyoInput')) {
					test = "true";
				}
			}
		
			if(test=="true"){
				defineSegmento();
			} else {	
				defineSegmentoAll();
			}
		}
		
		function validaFacturacionYBalance() {
		
			var factUltimoAnyoInput = toNumero(document.getElementById("factUltimoAnyoInput").value);
			var balanceUltimoAnyoInput = toNumero(document.getElementById("balanceUltimoAnyoInput").value);
			
			if(Number(factUltimoAnyoInput)>50000000){
				if(Number(balanceUltimoAnyoInput) > 43000000) {
					alert("Con esa Facturación y Balance, el segmento debe ser No Pyme");
					 	//defineSegmento();
					 	return true;
					}
				}
			return false;
		}
		
		function toNumero(s) {
			s = s.replace(".","");
			s = s.replace(".","");
			s = s.replace(".","");
			s = s.replace(".","");
			s = s.replace(",",".");
			s = s.replace(/\s/g,"");
			s = s.replace("€","");
			return s;
		}
		
		function validaNoSuperaTope(field) {
			const inputs = document.getElementById("altaForm").elements;
			const inputByName = inputs[field];

			if(Number(inputByName.value) > 250) {
				alert("Con ese número de empleados, el segmento debe ser No Pyme");
			 	//defineSegmento();
			 	return true;
			}
			return false;
		}

		function validaCampoNumerico(field) {
			const inputs = document.getElementById("altaForm").elements;
			const inputByName = inputs[field];

			if (!isNaN(inputByName.value)) {
				return true;
			}

			inputByName.value = "";
			alert("El valor definido no es válido.");

			return false;

		}
		/*
		function validaPorcentajePor() {
			const field = 'porcentajePorOtraInput';
			if(validaCampoNumerico(field)) {
			 //checkPorcentaje
			 return validaPorcentaje();
			 }
			 return false;
		}

		function validaPorcentajeEn() {
			const field = 'porcentajeEnOtraInput';
			if(validaCampoNumerico(field)) {
			 	//checkPorcentaje
			 	 return validaPorcentaje();
			 }
			 return false;
		}
		*/
		function validaPorcentaje() {
			const enField = 'porcentajeEnOtraInput';
			const porField = 'porcentajePorOtraInput';
			
			const inputs = document.getElementById("altaForm").elements;
			const inputByName1 = inputs[enField];
			const inputByName2 = inputs[porField];
			
			
			 //checkPOrcentaje
			 if((inputByName1.value>50) || (inputByName2.value>50)) {
			 	alert("Con estos porcentajes, el segmento debe ser No Pyme");
			 	//defineSegmento();
			 	return true;
			 } else {
				 //defineSegmentoAll();
				 	return false;
			 }
		}
		
		function defineSegmento() {
			const inputs = document.getElementById("altaForm").elements;
			const comboSegmento = inputs["segmento"];
			
			removeOptions(comboSegmento);
			var option = null;
			
			
			
			option = document.createElement("option");
			option.text = "NO PYME";
			option.value = "NO PYME";
			comboSegmento.add(option);

		}

		function defineSegmentoAll() {
			const inputs = document.getElementById("altaForm").elements;
			const comboSegmento = inputs["segmento"];
			
			removeOptions(comboSegmento);
			var option = null;
			
			option = document.createElement("option");
			option.text = "PYME";
			option.value = "PYME";
			comboSegmento.add(option);

			option = document.createElement("option");
			option.text = "NO PYME";
			option.value = "NO PYME";
			comboSegmento.add(option);

		}


		function validaCodigoPostal() {
			const inputs = document.getElementById("altaForm").elements;
			const inputByName = inputs["codPostInput"];
			cp = inputByName.value;

			if (validCpMx(cp) && cpAndalucia(cp)) {
				return true;
			} else {
				inputByName.value = "";
				alert("El CP definido no es válido.");
				return false;
			}

		}

		function validaCodigoPostalProyInv() {
			const inputs = document.getElementById("altaForm").elements;
			const inputByName = inputs["codPostProyInput"];
			cp = inputByName.value;

			if (validCpMx(cp) && cpAndalucia(cp)) {
				return true;
			} else {
				inputByName.value = "";
				alert("El CP definido no es válido.");
				return false;
			}

		}
		
		function toNumber(value) {
			const valor = value.replace(".","");
			valor = valor.replace("","");
			return new Intl.NumberFormat('es-ES', { style: 'currency', currency: 'EUR' }).format(valor);
		}

		function calculoImporteGarantiaBEI() {
			const inputs = document.getElementById("altaForm").elements;
			const inputByName = inputs["importeFinanciacionInput"];
			const outputByName = inputs["importeGarantiaBEIInput"];
			
			const num = formateaNumero(inputByName.value);
			const numero = (new Number(num) * 80) / 100;
			const result = numero.toLocaleString('es-ES', { style: 'currency', currency: 'EUR' });
			
			outputByName.value = result;
			
			}

		function calculoImporteDestinadoCirculante() {
			const inputs = document.getElementById("altaForm").elements;
			const inputImporteFinanciacion = inputs["importeFinanciacionInput"];
			const inputByName = inputs["importeDestinadoCirculanteInput"];

			if (!isNaN(formateaNumero(inputByName.value))
					&& !isNaN(formateaNumero(inputImporteFinanciacion.value))) {
					
				calc = (formateaNumero(inputImporteFinanciacion.value) * 30) / 100;

				if (Number(formateaNumero(inputByName.value)) < 200000
						|| Number(formateaNumero(inputByName.value)) < calc) {
					return true;
				} else {
					inputByName.value = "";
					alert("El Importe destinado a circulante no es válido.");
				}
			} else {
				inputByName.value = "";
				alert("El Importe destinado a circulante no es válido.");
			}

		}

		function checkGarantias() {
			const inputs = document.getElementById("altaForm").elements;
			const comboGarantias = inputs["garantiaAdicional"];
			const combo = inputs["tipoGarantiaAdicional"];

			if (comboGarantias.value == "SI") {
				combo.disabled = false;
				inputs["valorGarantiaAdicional"].readOnly = false;

			} else {
				inputs["valorGarantiaAdicional"].value = "";
				inputs["valorGarantiaAdicional"].readOnly = true;
				combo.disabled = true;
			}

		}

		var arrayDentro = [
				"0111 - Cultivo de cereales (excepto arroz), leguminosas y semillas oleaginosas",
				"0112 - Cultivo de arroz",
				"0113 - Cultivo de hortalizas, raíces y tubérculos",
				"0114 - Cultivo de caña de azúcar",
				"0116 - Cultivo de plantas para fibras textiles",
				"0119 - Otros cultivos no perennes",
				"0121 - Cultivo de la vid",
				"0122 - Cultivo de frutos tropicales y subtropicales",
				"0123 - Cultivo de cítricos",
				"0124 - Cultivo de frutos con hueso y pepitas",
				"0125 - Cultivo de otros árboles y arbustos frutales y frutos secos",
				"0126 - Cultivo de frutos oleaginosos",
				"0127 - Cultivo de plantas para bebidas",
				"0128 - Cultivo de especias, plantas aromáticas, medicinales y farmacéuticas",
				"0129 - Otros cultivos perennes",
				"0130 - Propagación de plantas",
				"0141 - Explotación de ganado bovino para la producción de leche",
				"0142 - Explotación de otro ganado bovino y búfalos",
				"0143 - Explotación de caballos y otros equinos",
				"0144 - Explotación de camellos y otros camélidos",
				"0145 - Explotación de ganado ovino y caprino",
				"0146 - Explotación de ganado porcino",
				"0147 - Avicultura",
				"0149 - Otras explotaciones de ganado",
				"0150 - Producción agrícola combinada con la producción ganadera",
				"0161 - Actividades de apoyo a la agricultura",
				"0162 - Actividades de apoyo a la ganadería",
				"0163 - Actividades de preparación posterior a la cosecha",
				"0164 - Tratamiento de semillas para reproducción",
				"1011 - Procesado y conservación de carne",
				"1012 - Procesado y conservación de volatería",
				"1013 - Elaboración de productos cárnicos y de volatería",
				"1031 - Procesado y conservación de patatas",
				"1032 - Elaboración de zumos de frutas y hortalizas",
				"1039 - Otro procesado y conservación de frutas y hortalizas",
				"1042 - Fabricación de margarina y grasas comestibles similares",
				"1043 - Fabricación de aceite de oliva",
				"1044 - Fabricación de otros aceites y grasas",
				"1053 - Fabricación de quesos",
				"1054 - Preparación de leche y otros productos lácteos",
				"1061 - Fabricación de productos de molinería",
				"1062 - Fabricación de almidones y productos amiláceos",
				"1081 - Fabricación de azúcar",
				"1083 - Elaboración de café, té e infusiones",
				"1091 - Fabricación de productos para la alimentación de animales de granja",
				"1092 - Fabricación de productos para la alimentación de animales de compañía",
				"1102 - Elaboración de vinos",
				"1103 - Elaboración de sidra y otras bebidas fermentadas a partir de frutas",
				"1104 - Elaboración de otras bebidas no destiladas, procedentes de la fermentación" ];

		var arrayFuera = [
				"10.52 - Elaboración de helados",
				"1071 - Fabricación de pan y de productos frescos de panadería y pastelería",
				"1072 - Fabricación de galletas y productos de panadería y pastelería de larga duración",
				"1073 - Fabricación de pastas alimenticias, cuscús y productos similares",
				"1082 - Fabricación de cacao, chocolate y productos de confitería",
				"1105 - Fabricación de cerveza" ];

		var arrayDentroFuera = [
				"1084 - Elaboración de especias, salsas y condimentos",
				"1101 - Destilación, rectificación y mezcla de bebidas alcohólicas",
				"1106 - Fabricación de malta" ];

		var arrayConsulta = [
				"1085 - Elaboración de platos y comidas preparados",
				"1086 - Elaboración de preparados alimenticios homogeneizados y alimentos dietéticos",
				"1089 - Elaboración de otros productos alimenticios n.c.o.p." ];

		var arrayConsultaFuera = [
				"1107 - Fabricación de bebidas no alcohólicas; producción de aguas minerales y otras aguas embotelladas" ];

		function loadComboCnae() {

			const inputs = document.getElementById("altaForm").elements;
			const comboCnae = inputs["cnae"];
			const comboDentroFuera = inputs["comboDentroFuera"];

			var option = null;

			for (value in arrayDentro) {
				option = document.createElement("option");
				option.text = arrayDentro[value];
				option.value = arrayDentro[value];
				comboCnae.add(option);
			}

			for (value in arrayFuera) {
				option = document.createElement("option");
				option.text = arrayFuera[value];
				option.value = arrayFuera[value];
				comboCnae.add(option);
			}

			for (value in arrayDentroFuera) {
				option = document.createElement("option");
				option.text = arrayDentroFuera[value];
				option.value = arrayDentroFuera[value];
				comboCnae.add(option);
			}

			for (value in arrayConsulta) {
				option = document.createElement("option");
				option.text = arrayConsulta[value];
				option.value = arrayConsulta[value];
				comboCnae.add(option);
			}
			
			for (value in arrayConsultaFuera) {
				option = document.createElement("option");
				option.text = arrayConsultaFuera[value];
				option.value = arrayConsultaFuera[value];
				comboCnae.add(option);
			}

			//Carga inicial
			option = document.createElement("option");
			option.text = "DENTRO";
			option.value = "DENTRO";
			comboDentroFuera.add(option);

		}

		function cambioCnae() {
			const inputs = document.getElementById("altaForm").elements;
			const comboCnae = inputs["cnae"];
			const comboDentroFuera = inputs["comboDentroFuera"];

			removeOptions(comboDentroFuera);
			var option = null;

			if (arrayContains(comboCnae.value, arrayDentro)) {
				//DENTRO
				option = document.createElement("option");
				option.text = "DENTRO";
				option.value = "DENTRO";
				comboDentroFuera.add(option);
			} else if (arrayContains(comboCnae.value, arrayFuera)) {
				//FUERA
				option = document.createElement("option");
				option.text = "FUERA";
				option.value = "FUERA";
				comboDentroFuera.add(option);
			} else if (arrayContains(comboCnae.value, arrayDentroFuera)) {
				//DENTRO/FUERA
				option = document.createElement("option");
				option.text = "DENTRO";
				option.value = "DENTRO";
				comboDentroFuera.add(option);

				option = document.createElement("option");
				option.text = "FUERA";
				option.value = "FUERA";
				comboDentroFuera.add(option);
			} else if (arrayContains(comboCnae.value, arrayConsulta)) {
				
				//DENTRO
				option = document.createElement("option");
				option.text = "DENTRO";
				option.value = "DENTRO";
				comboDentroFuera.add(option);
		
				option = document.createElement("option");
				option.text = "FUERA";
				option.value = "FUERA";
				comboDentroFuera.add(option);
		
				//CONSULTA
				option = document.createElement("option");
				option.text = "CONSULTA";
				option.value = "CONSULTA";
				comboDentroFuera.add(option);
			} else if (arrayContains(comboCnae.value, arrayConsultaFuera)) {
				//FUERA
				option = document.createElement("option");
				option.text = "FUERA";
				option.value = "FUERA";
				comboDentroFuera.add(option);
				
				option = document.createElement("option");
				option.text = "CONSULTA";
				option.value = "CONSULTA";
				comboDentroFuera.add(option);
			}

		}

		function removeOptions(combo) {
			var i, l = combo.options.length - 1;

			for (i = l; i >= 0; i--) {
				combo.remove(i);
			}
		}

		function arrayContains(needle, arrhaystack) {
			return (arrhaystack.indexOf(needle) > -1);
		}

		function checkInversionActividadesEsp() {
			const inputs = document.getElementById("altaForm").elements;
			const comboAut = inputs["inversionActividadesAutorizacionEsp"];
						
			if (comboAut.value == "SI") {
				visibility("autorizacionEspecificaLabel", "s");
				visibility("autorizacionLabel", "s");
				
			} else {
				visibility("autorizacionEspecificaLabel", "n");
				visibility("autorizacionLabel", "n");
			}

		}

		function checkInversionEnRegadio() {
			const inputs = document.getElementById("altaForm").elements;
			const comboAut = inputs["inversionEnRegadio"];
		
			if (comboAut.value == "SI") {
				visibility("documentosLabel", "s");
			
				visibility("certificadoLabel", "s");
			
				visibility("resolucionLabel", "s");
			
				visibility("compromisoLabel", "s");
			
			} else {
				visibility("documentosLabel", "n");
			
				visibility("certificadoLabel", "n");
			
				visibility("resolucionLabel", "n");
			
				visibility("compromisoLabel", "n");
			
			}
		}

		function visibility(id, visible) {
			var vis = 'block';
			if (visible == "n") {
				vis = 'none';
			}

			document.getElementById(id).style.display = vis;
		}

		function required(id, req) {
			document.getElementById(id).required = req;
		}
		
		function selectCombo(id, position) {
			document.getElementById(id).selectedIndex = position;
		}
		
		
		function setCombosInversion() {
			selectCombo('inversionActividadesAutorizacionEsp',1);
			selectCombo('inversionEnRegadio',1);
		}
		
		function checkBo(){
			var perfil = document.getElementById("perfil").value;
			if(perfil == "bo") {
				document.getElementById("oficinaInput").readOnly = false;
			}
		}
		
		function submitNoPyme() {
				const form = document.getElementById("calculadoraForm");
				const oficina = document.getElementById("oficina").value;
				const id = document.getElementById("idSolicitud").value;
				const perfil = document.getElementById("perfil").value;
				const numOficinaPerfil = document.getElementById("numOficinaPerfil").value;
							
				form.action= '/calculoNoPyme/'+oficina+'/'+id+'/'+perfil+'/'+numOficinaPerfil;
				form.method="POST";
				
				form.submit();
		}
		
		function submitPyme() {
				const form = document.getElementById("calculadoraForm");
				const oficina = document.getElementById("oficina").value;
				const id = document.getElementById("idSolicitud").value;
				const perfil = document.getElementById("perfil").value;
				const numOficinaPerfil = document.getElementById("numOficinaPerfil").value;
							
				form.action= '/calculoPyme/'+oficina+'/'+id+'/'+perfil+'/'+numOficinaPerfil;
				form.method="POST";
				
				form.submit();
		}
		
		function downloadExcelPyme() {
				const form = document.getElementById("calculadoraForm");
				const oficina = document.getElementById("oficina").value;
				const id = document.getElementById("idSolicitud").value;
				const perfil = document.getElementById("perfil").value;
				const numOficinaPerfil = document.getElementById("numOficinaPerfil").value;
							
				form.action= '/downloadExcelPyme/'+oficina+'/'+id+'/'+perfil+'/'+numOficinaPerfil;
				form.method="POST";
				
				form.submit();
		}
		
		
		function validarSolicitud(){
				var result = null;
				const finalidad = document.getElementById("finalidad").value;
				const comboDentroFuera = document.getElementById("comboDentroFuera").value;
				const segmento = document.getElementById("segmento").value;
				var esb = null;
				var importeAyudasInput = document.getElementById("importeAyudasInput").value;
				var importeFinanciacionInput = document.getElementById("importeFinanciacionInput").value;
			
				var resultadoValidacion = document.getElementById("resultadoValidacion");
				
				if(segmento=="PYME") {
					esb = document.getElementById("esbPymeCalculado").value;
				} else	if(segmento=="NO PYME") {
					esb = document.getElementById("esbNoPymeCalculado").value;
				}
				
				if(esb=="") {
					return;
				}
				
				if(finalidad=="Inversion") {
					if(comboDentroFuera=="DENTRO") {
					//Dentro
							var sum = Number(formateaNumero(esb)) + Number(formateaNumero(importeAyudasInput));
							if(sum<=(Number(formateaNumero(importeFinanciacionInput))/2)) {
								result = "OK";
							} else {
								result = "KO";
							}
					} else 
					if(comboDentroFuera=="FUERA") {
					//Fuera
						if(sum<=(Number(formateaNumero(importeFinanciacionInput))/2)) {
							if(sum<=200000) {
								result = "OK";
							} 
						} else {
								result = "KO";
						}
					}
				
				}
			
			 document.getElementById("resultadoValidacion").value=result;
		}
		
		/*
			
			 //Funcion Filtro numero sia
		$(document).ready(function() {
			  $("#numeroSIA").on("keyup", function() {
			    var value = $(this).val().toLowerCase();
			    $("#myTable tr").filter(function() {
			      $(this).toggle($(this).find("td:first").text().toLowerCase().indexOf(value) > -1)
			    });
			  });
			});
			
	//Funcion filtro CIF
		$(document).ready(function() {
			  $("#CIF").on("keyup", function() {
			    var value = $(this).val().toLowerCase();
			    $("#myTable tr").filter(function() {
			      $(this).toggle($(this).find("td:eq(1)").text().toLowerCase().indexOf(value) > -1)
			    });
			  });
			});
	//Funcion filtro oficina
		$(document).ready(function() {
			  $("#oficina").on("keyup", function() {
			    var value = $(this).val().toLowerCase();
			    $("#myTable tr").filter(function() {
			      $(this).toggle($(this).find("td:eq(3)").text().toLowerCase().indexOf(value) > -1)
			    });
			  });
			});
	//funcion filtro fecha
		$(document).ready(function() {
			  $("#fecha").on("keyup", function() {
			    var value = $(this).val().toLowerCase();
			    $("#myTable tr").filter(function() {
			      $(this).toggle($(this).find("td:eq(4)").text().toLowerCase().indexOf(value) > -1)
			    });
			  });
			});
	
	//Funcion recoger valor select estado y meterlo en input
		$(function() {
			$('select.estado').change(function() {
				var typeId = $(this).find('option:selected').data('typeid');
				$("#estadoInput").val(typeId);
			}).change();
		});
	//Funcion filtro estado
		$(document).ready(function() {
			  $("#estadoInput").on("click", function() {
			    var value = $(this).val().toLowerCase();
			    $("#myTable tr").filter(function() {
			      $(this).toggle($(this).find("td:eq(5)").text().toLowerCase().indexOf(value) > -1)
			    });
			  });
			});
			
					$(document).ready(function(){
		  $("#myInput").on("keyup", function() {
		    var value = $(this).val().toLowerCase();
		    $("#numeroSia tr").filter(function() {
		      $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
		    });
		  });
		});
		*/
