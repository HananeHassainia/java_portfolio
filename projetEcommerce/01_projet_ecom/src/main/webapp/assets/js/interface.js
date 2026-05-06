/**
 * 
 */

/* ===============================Carousselle================================ */

$(function() {
	// Déclaration et instanciation des variables nbr(le nombre de slide du
	// carrousel)
	// et p : position dans le carrousel (par défaut 0 : à l'ouverture de la
	// fenêtre c'est la première slide qui est affichée)
	var nbr = 2;
	var p = 0;

	// Récupération de certains éléments du fichier html
	var container = $('#container');
	var d = $('#d');
	var g = $('#g');
	var dots = document.getElementsByClassName("dot");

	// liste qui stocke les id des trois éléments compris dans le carrousel
	var list = [ 'page1', "page2" ];

	// fonction appelé lors du clique sur le bouton d
	// modification de la manière dont sont affichés les éléments du carrousel :
	// Un élément a pour caractéristique 'display : block' et les deux autres
	// 'display:none' ainsi un seul élément est affiché à l'écran
	// l'élement change en fonction du clique sur le bouton
	d.click(function() {
		if (p < 1) {
			p++;
			for (let i = 0; i < 2; i++) {
				if (i == p) {
					dots[i].className += " active";
					document.getElementById(list[i]).style.display = 'grid';
				} else {
					dots[i].className = dots[i].className
							.replace(" active", "");
					document.getElementById(list[i]).style.display = 'none';
				}
			}
		} else {
			p = 0;
			dots[0].className += " active";
			dots[1].className = dots[1].className.replace(" active", "");

			$('#page1').css('display', 'grid');
			$('#page2').css('display', 'none');
		}
	})

	// Fonctionne d'une manière similaire au bouton d
	g.click(function() {
		if (p > 0) {
			p--;
			for (let i = 0; i < 2; i++) {
				if (i == p) {
					dots[i].className += " active";
					document.getElementById(list[i]).style.display = 'grid';
				} else {
					document.getElementById(list[i]).style.display = 'none';
					dots[i].className = dots[i].className
							.replace(" active", "");

				}
			}
		} else {
			p = 1;
			dots[0].className = dots[0].className.replace(" active", "");
			dots[1].className += " active";
			$('#page1').css('display', 'none');
			$('#page2').css('display', 'grid');
		}
	})
});

/*
 * ===============================Changer couleur bouton cat quand dans cette
 * categorie================================
 */

function changerCouleurNav() {
	let cat = $("#navColor").text();
	let boutonNavCat = $('.boutonNavCat button');
	idCat = "#" + cat;
	$("#" + cat).css("background-color", "rgb(197, 167, 142)");
}

const euro = new Intl.NumberFormat('fr-FR', {
	style : 'currency',
	currency : 'EUR',
	minimumFractionDigits : 2
});

function Promotion() {
	let inputPromo = $('#inputTestPromo');
	console.log("promotion")
	let divSansPromo = $("#totalSansReduc");
	let divAvecPromo = $("#totalAvecReduc");
	let totalPrix = $('#totalPrix');
	let resetBtn = $("#buttonNoPromo");

	inputPromo.on("input", function() {
		console.log("okkk input");
		if (inputPromo.val() == "Nomane2022") {
			console.log("total avant reduc : " + $("#prixT").text())
			let newTotal = ($("#prixT").text() * (1 - 30 / 100)).toFixed(2);
			totalPrix.text(euro.format(newTotal));
			divSansPromo.css("display", "none");
			divAvecPromo.css("display", "initial");
		} else {
			inputPromo.css("border", "2px solid red");
		}
	});

	resetBtn.click(function() {
		inputPromo.val("");
		inputPromo.css("border", "1px solid gray")
		divSansPromo.css("display", "initial");
		divAvecPromo.css("display", "none");
	})
}

$(function() {
	console.log("The URL of this page is: " + window.location.href);
	if (window.location.href == "http://localhost:8080/01_projet_ecom/displayProdByCat") {
		changerCouleurNav();
	}
	Promotion();
})

/*
 * ===============================fonction de dropdown de espace
 * clien===================================
 */
function myFunction() {
	document.getElementById("myDropdown").classList.toggle("show");
}

// Close the dropdown if the user clicks outside of it
window.onclick = function(e) {
	if (!e.target.matches('.dropbtn')) {
		var myDropdown = document.getElementById("myDropdown");
		if (myDropdown.classList.contains('show')) {
			myDropdown.classList.remove('show');
		}
	}
}

