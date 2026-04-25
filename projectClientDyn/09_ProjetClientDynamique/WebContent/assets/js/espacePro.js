/**
 * 
 */
$(function() {

	//Gestion checkbox
	$('.checkIn').on('change', function() {
		console.log("ok checkbox");
		$('.checkIn').not(this).prop('checked', false);
	});

	$('.opeCheck').on('change', function() {
		console.log("ok checkbox");
		$('.opeCheck').not(this).prop('checked', false);
		if($('#depCheck').is(':checked')){
			$("#formDepot").css("display","initial");
			$("#formVir").css("display","none");
			$("#formRetrait").css("display","none");
		}
		else if($('#virCheck').is(':checked')){
			$("#formDepot").css("display","none");
			$("#formVir").css("display","initial");
			$("#formRetrait").css("display","none");
		}
		else {
			$("#formDepot").css("display","none");
			$("#formVir").css("display","none");
			$("#formRetrait").css("display","initial");
		}
	});

	
	// Gestion recherche id
	var linkModif1 = "http://localhost:8080/09_ProjetClientDynamique/getCE";
	var linkModif2 = "http://localhost:8080/09_ProjetClientDynamique/homeCE";
	var linkModif3 = "http://localhost:8080/09_ProjetClientDynamique/deleteCE";
	var linkModif4 = "http://localhost:8080/09_ProjetClientDynamique/opRCE";
	var modifLinks = [ linkModif1, linkModif2, linkModif3, linkModif4 ];

	if (modifLinks.includes(window.location.href)) {
		$('#idIdCe').html('mismatch');
		$('#idIdCe')[0].setCustomValidity('Veuillez entrer un id correct');

	}

	// add btn on click chercher ==> if mismatch ==> red

	function chercherId() {
		$('#idIdCe')
				.on(
						"input",
						function() {
							let listId = [];
							let listIdClt = [];
							$(".listeAll").each(function() {
								listId.push($(this).text());
							});
							$(".listeAllClt").each(function() {
								listIdClt.push($(this).text());
							});
							let value = $('#idIdCe').val();

							if ($('#IdcltCheck').is(":checked")) {
								console.log("checked");
								if (listIdClt.includes(value)) {
									$('#idIdCe').html('match');
									this.setCustomValidity('');
									$('#idIdCe').css("border",
											"2px solid green");
								}

								else {
									$('#idIdCe').html('mismatch');
									$('#idIdCe').css("border", "2px solid red");
									this
											.setCustomValidity('Test 3 Veuillez entrer un id correct');
								}
							} else {
								console.log("pas check");
								let value = $('#idIdCe').val();

								if (listId.includes(value)) {
									$('#idIdCe').html('match');
									this.setCustomValidity('');
									$('#idIdCe').css("border",
											"2px solid green");

								} else {
									console.log("pas pareil");
									$('#idIdCe').html('mismatch');
									$('#idIdCe').css("border", "2px solid red");
									this
											.setCustomValidity('Test 4 Veuillez entrer un id correct');

								}
							}

						})
	}

	chercherId();
	$('#IdcltCheck').on('change', function() {
		let listId = [];
		let listIdClt = [];
		$(".listeAll").each(function() {
			listId.push($(this).text());
		});
		$(".listeAllClt").each(function() {
			listIdClt.push($(this).text());
		});

		let value = $('#idIdCe').val();

		if ($('#IdcltCheck').is(":checked")) {
			console.log("checked");
			if (listIdClt.includes(value)) {
				$('#idIdCe').html('match');
				this.setCustomValidity('');
				$('#idIdCe').css("border", "2px solid green");
			}

			else {
				$('#idIdCe').html('mismatch');
				$('#idIdCe').css("border", "2px solid red");
				this.setCustomValidity('Test 3 Veuillez entrer un id correct');
			}
		} else {
			console.log("pas check");

			if (listId.includes(value)) {
				$('#idIdCe').html('match');
				this.setCustomValidity('');
				$('#idIdCe').css("border", "2px solid green");

			} else {
				console.log("pas pareil");
				$('#idIdCe').html('mismatch');
				$('#idIdCe').css("border", "2px solid red");
				this.setCustomValidity('Test 4 Veuillez entrer un id correct');

			}
		}
		chercherId();
	});
	
	$('#IdcltCheck').on('change', function() {
		let listId = [];
		let listIdClt = [];
		$(".listeAll").each(function() {
			listId.push($(this).text());
		});
		$(".listeAllClt").each(function() {
			listIdClt.push($(this).text());
		});

		let value = $('#idIdCe').val();

		if ($('#IdcltCheck').is(":checked")) {
			console.log("checked");
			if (listIdClt.includes(value)) {
				$('#idIdCe').html('match');
				this.setCustomValidity('');
				$('#idIdCe').css("border", "2px solid green");
			}

			else {
				$('#idIdCe').html('mismatch');
				$('#idIdCe').css("border", "2px solid red");
				this.setCustomValidity('Veuillez entrer un id correct');
			}
		} else {
			console.log("pas check");

			if (listId.includes(value)) {
				$('#idIdCe').html('match');
				this.setCustomValidity('');
				$('#idIdCe').css("border", "2px solid green");

			} else {
				console.log("pas pareil");
				$('#idIdCe').html('mismatch');
				$('#idIdCe').css("border", "2px solid red");
				this.setCustomValidity('Veuillez entrer un id correct');

			}
		}
		chercherId();
	});

	// Chercher id Client
	function chercherIdClt() {
		$('#idIdClt').on("input", function() {
			let listIdClt = [];
			console.log("valllllllll = " + listIdClt);
			console.log($('#idIdClt'));
			$("#listId option").each(function() {
				console.log("value =" + $(this).val());
				listIdClt.push($(this).val());
			});// Add $(this).
			console.log("liste = " + listIdClt);
			let value = $('#idIdClt').val();

			if (listIdClt.includes(value)) {
				$('#idIdClt').html('match');
				this.setCustomValidity('');
				$('#idIdClt').css("border", "2px solid green");

			} else {
				console.log("pas pareil");
				$('#idIdClt').html('mismatch');
				$('#idIdClt').css("border", "2px solid red");
				this.setCustomValidity('Veuillez entrer un id correct');
			}
		})
	}
	;

	chercherIdClt();
	
	// Gestion mdp cons
	var linkMdpC1 = "http://localhost:8080/09_ProjetClientDynamique/mdpCons.jsp";

	console.log("feneentre : " + window.location.href);
	console.log("dans la bonne fenêter");

	if (linkMdpC1 == window.location.href) {
		let mdp = $('.mdpSession').text();
		let inputOld = $('#idmdpOld');
		let inputNew = $('#idmdpNew');
		let inputConf = $('#idmdpConfirm');

		inputOld.on("input", function() {
			if (mdp == inputOld.val()) {
				console.log(inputOld.val());
				inputOld.html('match');
				this.setCustomValidity('');
				inputOld.css("border", "3px solid green");
				inputNew.prop("disabled", false);
				inputConf.prop("disabled", false);

			}

			else {
				console.log("revenue dans else de input old");
				inputOld.html('mismatch');
				inputOld.css("border", "2px solid red");
				this.setCustomValidity('Votre mot de passe est incorrect');
				inputNew.prop("disabled", true);
				inputConf.prop("disabled", true);
			}
		})

		inputConf
				.on(
						"input",
						function() {
							console.log("conf =" + inputConf.val());
							console.log("new =" + inputNew.val());
							if (inputNew.val() != mdp) {
								if (inputNew.val() == inputConf.val()) {
									console.log(inputOld.val());
									inputConf.html('match');
									this.setCustomValidity('');
									inputNew.css("border", "3px solid green");
									inputConf.css("border", "3px solid green");
								}

								else {
									console.log("mdp = " + inputOld.val());
									console.log("mdp avant = " + mdp);
									inputConf.html('mismatch');
									inputConf.css("border", "2px solid red");
									this
											.setCustomValidity(' Test 1 Veuillez entrer deux mots de passe identique de minimum 5 caractères');
								}
							} else {
								if (inputNew.val() == inputConf.val()) {
									console.log(inputOld.val());
									inputConf.html('match');
									this.setCustomValidity('');
									inputConf.css("border", "3px solid green");
								}

								else {
									console.log("mdp = " + inputOld.val());
									console.log("mdp avant = " + mdp);
									inputConf.html('mismatch');
									inputConf.css("border", "2px solid red");
									this
											.setCustomValidity(' Test 1 Veuillez entrer deux mots de passe identique de minimum 5 caractères');
								}
							}

						})

		inputNew
				.on(
						"input",
						function() {
							console.log("conf =" + inputConf.val());
							console.log("new =" + inputNew.val());
							if (inputNew.val() != mdp) {
								if (inputNew.val() == inputConf.val()) {
									console.log(inputOld.val());
									inputConf.html('match');
									this.setCustomValidity('');
									inputNew.css("border", "3px solid green");
									inputConf.css("border", "3px solid green");
								}

								else {
									console.log("mdp = " + inputOld.val());
									console.log("mdp avant = " + mdp);
									inputConf.html('mismatch');
									inputConf.css("border", "2px solid red");
									inputConf[0]
											.setCustomValidity('Test 2 Veuillez entrer deux mots de passe identique de minimum 5 caractères');
								}
							} else {
								console.log("mdp = " + inputOld.val());
								console.log("mdp avant = " + mdp);
								inputNew.html('mismatch');
								inputNew.css("border", "2px solid red");
								this
										.setCustomValidity("Veuillez entrer un mot de passe différent de l'ancien");
							}
						})

		var togglePassword = document.querySelector("#togglePassword");
		var togglePassword2 = document.querySelector("#togglePassword2");
		var togglePassword3 = document.querySelector("#togglePassword3");

		togglePassword.addEventListener("click", function() {
			console.log("okkk");
			console.log("type of =" + inputNew.attr("type"));
			const type = inputNew.attr("type") === "password" ? "text"
					: "password";
			inputNew.attr("type", type);

			// toggle the icon
			this.classList.toggle("bi-eye");
		});

		togglePassword2.addEventListener("click", function() {
			const type = inputConf.attr("type") === "password" ? "text"
					: "password";
			inputConf.attr("type", type);

			// toggle the icon
			this.classList.toggle("bi-eye");
		});

		togglePassword3.addEventListener("click", function() {
			const type = inputOld.attr("type") === "password" ? "text"
					: "password";
			inputOld.attr("type", type);

			// toggle the icon
			this.classList.toggle("bi-eye");
		});

	}

	$("#btnModifFirst").click(function() {
		$("#divModif").css("display", "initial");
//		 $("body").not("#inputModif, #annulerBtn2").css("pointer-events","none");
	})

	$("#btnSuppFirst").click(function() {
		console.log("okau click bouton");
		$("#divSupp").css("display", "initial");
//		 $("body > *").not("#inputSupp, #annulerBtn1").css("pointer-events","none");

	})
	
	$("#annulerBtn1").click(function(){
		console.log("oook btn");
		$(".divConfirm").css("display", "none");
	})
	
	$("#annulerBtn2").click(function(){
		console.log("oook btn");

		$(".divConfirm").css("display", "none");
	})

})
