let createid;
let viewid;
$(document).ready(function() {

    $(document).on("submit", "#login", function(event){
        $.ajax({
            type: $("#createaddressbook").attr("method"),
            url: '/createaddressbook'
        }).done(function(response) {
            let body = response.substring(response.indexOf("<body>")+6,response.indexOf("</body>"));
            $("body").html(body);
        });
        event.preventDefault();
    });

});