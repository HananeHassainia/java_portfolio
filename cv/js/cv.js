/*
Projet CV
HASSAINIA Hanane
04/03/2022
*/

//Fonction qui permet de cacher ou d'afficher le contenu des tables ou des div (table) situées en dessous des (titres)
function hide(titre, table){
    //Récupération des deux éléments par leur id (la table que l'on veut cacher et le titre correspondant)
    let titreFormation = document.getElementById(titre);
    let tableFormation = document.getElementById(table);

    //Vérifier si la table est actuellement afficher ou non 

    //Si elle est n'est pas afficher elle sera afficher au clique sur le bouton
    if(tableFormation.style.display == "none"){
        tableFormation.style.display = "table";
        titreFormation.value = "-";
        titreFormation.style.backgroundColor ="white" ;
        titreFormation.style.color = "rgb(59, 134, 156)";
        titreFormation.style.paddingBottom ="0%";
    }

    //Si elle est affiché, elle va être cachée au clique de ce même bouton
    else{
        tableFormation.style.display = "none";
        titreFormation.value = "+";
        titreFormation.style.backgroundColor = "rgb(59, 134, 156)";
        titreFormation.style.color = "white";
}
}


//Fonction qui permet de faire l'animation avec les ballons
//Elle prend en paramètre le nombre de ballons souhaité
function timeBallon(num){
    let id = null;
    clearInterval(id);
    id = setInterval(createBalloons(num) ,3);

    //Fonction qui permet de récupérer un entier parmis un nombre choisi de manière aléatoire entre 0 et num
    function random(num) {
        return Math.floor(Math.random()*num)
    }

    //Fonction qui permet de générer un style aléatoire (couleur, ombre, margin, et duré de l'animation)
    function getRandomStyles() {
        var r = random(255);
        var g = random(255);
        var b = random(255);
        var mt = random(200);
        var ml = random(50);
        var dur = random(5)+5;
        return `
        background-color: rgba(${r},${g},${b},0.7);
        color: rgba(${r},${g},${b},0.7); 
        box-shadow: inset -7px -3px 10px rgba(${r-10},${g-10},${b-10},0.7);
        margin: ${mt}px 0 0 ${ml}px;
        animation: float ${dur}s linear
        `
    }

    //Création des ballons de l'animation stocké dans la div déja crée (divBallon)
    //chaque ballon à un style random grâce à la fonction getRandomStyles
    function createBalloons(num) {
        $('#divBallon').append("<div id='balloon-container'></div>");
        var balloonContainer = $("#balloon-container");
        for (var i = 0; i < num; i++) {
            var balloon = document.createElement("div");
            balloon.className = "balloon";
            balloon.id = "balloon"+i;
            balloon.style.cssText = getRandomStyles();           
            balloonContainer.append(balloon);
        }
      
      //Un ballon disparait à la fin de son animation
      for(let elem of $('.balloon')){
            elem.addEventListener("animationend", function(){
               elem.remove();
            });
      }
    }
}


//fonction qui est utilisé après le chargement de la fenêtre
$(function(){
   //Déclaration et instanciation des variables nbr(le nombre de slide du carrousel) 
   //et p : position dans le carrousel (par défaut 0 : à l'ouverture de la fenêtre c'est la première slide qui est affichée)
   var nbr=3;
   var p=0;

   //Récupération de certains éléments du fichier html
   var container = $('#container');
   var d = $('#d');
   var g = $('#g');

   //liste qui stocke les id des trois éléments compris dans le carrousel
   var list = ['page1',"page2",'page3'];
   
   //fonction appelé lors du clique sur le bouton d
   //modification de la manière dont sont affichés les éléments du carrousel : 
   //Un élément a pour caractéristique 'display : block' et les deux autres 'display:none' ainsi un seul élément est affiché à l'écran 
   //l'élement change en fonction du clique sur le bouton
   d.click(function(){
       if(p<2){
           p++;
           for(let i=0; i<3; i++){
               if(i==p){
                   console.log(document.getElementById(list[i]));
                    document.getElementById(list[i]).style.display='block';   
               }
               else{
                    document.getElementById(list[i]).style.display='none';   
               }
           }
       }
       else{
           p=0;
           $('#page1').css('display','block');
           $('#page2').css('display','none');
           $('#page3').css('display','none');
       }
   })

   //Fonctionne d'une manière similaire au bouton d
   g.click(function(){
    if(p>0){
        p--;
        for(let i=0; i<3; i++){
            if(i==p){
                console.log(document.getElementById(list[i]));
                document.getElementById(list[i]).style.display='block';   
            }
            else{
                document.getElementById(list[i]).style.display='none';   
            }
        }
    }
    else{
           p=2;
           $('#page1').css('display','none');
           $('#page2').css('display','none');
           $('#page3').css('display','block');
       }
   })
});

