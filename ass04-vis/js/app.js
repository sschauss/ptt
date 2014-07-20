var megalElements = document.querySelectorAll('[data-megal]');
var megalMouseOverCallback = function(e) {
    var m = e.target.dataset.megal;
    for(var i = 0; i < megalElements.length; i++){
        if(megalElements[i].dataset.megal === m) {
            megalElements[i].className = 'highlight';
        }
    }
};

var megalMouseOutCallback = function(e) {
    var m = e.target.dataset.megal;
    for(var i = 0; i < megalElements.length; i++){
        if(megalElements[i].dataset.megal === m) {
            megalElements[i].className = '';
        }
    }
};

for(var i = 0; i < megalElements.length; i++){
    megalElements[i].addEventListener('mouseover', megalMouseOverCallback);
    megalElements[i].addEventListener('mouseout', megalMouseOutCallback);
}


