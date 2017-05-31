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
            $("#slider-range").slider({
                range: true,
                min: 0,
                max: 500,
                values: [0, 500],
                slide: function (event, ui) {
                    $("[name='minPrice']").val($("#slider-range").slider("values", 0));
                    $("[name='maxPrice']").val($("#slider-range").slider("values", 1));
                },
                create: function (event, ui) {
                    $("[name='minPrice']").val($("#slider-range").slider("values", 0));
                    $("[name='maxPrice']").val($("#slider-range").slider("values", 1));
                },
                stop: function(event, ui) {
                    console.log("STOP");
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