(function(){
    var init=function(){
        var owl;

        $('.bs-carousel-popular').owlCarousel({
            loop:true,
            margin:10,
            nav:false,
            dots:false,
            autoplay: true,
            autoplayTimeout: 5000,
            responsive:{
                0:{
                    items:1
                },
                600:{
                    items:3
                },
                1000:{
                    items:4
                }
            }
        });

        $('.bs-carousel-favourite').owlCarousel({
            loop:true,
            margin:10,
            nav:false,
            dots:false,
            autoplay: true,
            autoplayTimeout: 5000,
            responsive:{
                0:{
                    items:1
                },
                600:{
                    items:3
                },
                1000:{
                    items:4
                }
            }
        });

        $('.bs-carousel-newest').owlCarousel({
            loop:true,
            margin:10,
            nav:false,
            dots:false,
            autoplay: true,
            autoplayTimeout: 5000,
            responsive:{
                0:{
                    items:1
                },
                600:{
                    items:3
                },
                1000:{
                    items:4
                }
            }
        });

        $('.bs-carousel-suggestion').owlCarousel({
            loop:true,
            margin:10,
            nav:false,
            dots:false,
            autoplay: true,
            autoplayTimeout: 5000,
            responsive:{
                0:{
                    items:1
                },
                600:{
                    items:4
                },
                1000:{
                    items:6
                }
            }
        });

        //Get elements carousel
        popular=$('.bs-carousel-popular').owlCarousel();
        newest=$('.bs-carousel-newest').owlCarousel();
        favourite=$('.bs-carousel-favourite').owlCarousel();
        suggestion=$('.bs-carousel-suggestion').owlCarousel();

        // Popular carousel
        $(".bs-prev-popular").click(function () {
            popular.trigger('prev.owl.carousel');
        });

        $(".bs-next-popular").click(function () {
            popular.trigger('next.owl.carousel');
        });

        // Newest carousel
        $(".bs-prev-newest").click(function () {
            newest.trigger('prev.owl.carousel');
        });

        $(".bs-next-newest").click(function () {
            newest.trigger('next.owl.carousel');
        });

        // Favorite carousel
        $(".bs-prev-favourite").click(function () {
            favourite.trigger('prev.owl.carousel');
        });

        $(".bs-next-favourite").click(function () {
            favourite.trigger('next.owl.carousel');
        });

        // Suggestion carousel
        $(".bs-prev-suggestion").click(function () {
            suggestion.trigger('prev.owl.carousel');
        });

        $(".bs-next-suggestion").click(function () {
            suggestion.trigger('next.owl.carousel');
        });

        //Rangle slider
        $(function () {
            console.log("Limit price: "+$("#limitPrice").val());
            console.log("Min price: "+$("[name='minPrice']").val());
            console.log("Max price: "+$("[name='maxPrice']").val());

            $("#slider-range").slider({
                range: true,
                min: 0,
                max: $("#limitPrice").val(),
                values: [$("[name='minPrice']").val(), $("[name='maxPrice']").val()],
                slide: function (event, ui) {
                    $("[name='minPrice']").val($("#slider-range").slider("values", 0));
                    $("[name='maxPrice']").val($("#slider-range").slider("values", 1));
                },
                create: function (event, ui) {
                    //$("[name='minPrice']").val($("#slider-range").slider("values", 0));
                    //$("[name='maxPrice']").val($("#slider-range").slider("values", 1));
                },
                stop: function(event, ui) {
                    var url=$('#formPrice').attr("action");
                    var val1=$("[name='minPrice']").val();
                    var val2=$("[name='maxPrice']").val();

                    var price=/\/price\/[\d]+-[\d]+\//;
                    var order=/\/order\/[\d]+\//;
                    var page=/\/page\/[\d]+\//;
                    var book=/\/books\/[\d]+\//;

                    var targetUrl="";
                    console.log(url);
                    console.log(price.test(url));
                    console.log(order.test(url));
                    console.log(book.test(url));

                    if(book.test(url)){
                        targetUrl+=book.exec(url)[0]+"page/1/";
                    }

                    /*
                    if(price.test(url)){
                        targetUrl+=price.exec(url)[0].substring(1);
                    }*/

                    targetUrl+="price/"+val1+"-"+val2+"/";

                    console.log(targetUrl);
                    if(order.test(url)){
                        targetUrl+=order.exec(url)[0].substring(1);
                    }
                    console.log(targetUrl);
                    $('#formPrice').attr("action",targetUrl);
                    $('#formPrice').submit();
                }
            });
        });

        //Rating stars
        $(function () {
            var i=0;
            var count=$('#countViews').val();
            console.log(count);
            for(i=0;i<count;i++){
                $("#reviewRate"+i).barrating({
                    theme: 'bootstrap-stars',
                    readonly: true
                });
            }

            $("#rate").barrating({
                theme: 'bootstrap-stars'
            });
        });
    };

    init();
    return {

    };
})();