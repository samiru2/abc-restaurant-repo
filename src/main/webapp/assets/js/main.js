(function() {
  "use strict";

  /**
   * Apply .scrolled class to the body as the page is scrolled down
   */
  function toggleScrolled() {
    var selectBody = document.querySelector('body');
    var selectHeader = document.querySelector('#header');
    if (!selectHeader.classList.contains('scroll-up-sticky') && !selectHeader.classList.contains('sticky-top') && !selectHeader.classList.contains('fixed-top')) return;
    if (window.scrollY > 100) {
      selectBody.classList.add('scrolled');
    } else {
      selectBody.classList.remove('scrolled');
    }
  }

  document.addEventListener('scroll', toggleScrolled);
  window.addEventListener('load', toggleScrolled);

  /**
   * Mobile nav toggle
   */
  var mobileNavToggleBtn = document.querySelector('.mobile-nav-toggle');

  function mobileNavToogle() {
    document.querySelector('body').classList.toggle('mobile-nav-active');
    mobileNavToggleBtn.classList.toggle('bi-list');
    mobileNavToggleBtn.classList.toggle('bi-x');
  }
  if (mobileNavToggleBtn) {
    mobileNavToggleBtn.addEventListener('click', mobileNavToogle);
  }

  /**
   * Hide mobile nav on same-page/hash links
   */
  var navmenuLinks = document.querySelectorAll('#navmenu a');
  Array.prototype.forEach.call(navmenuLinks, function(navmenu) {
    navmenu.addEventListener('click', function() {
      if (document.querySelector('.mobile-nav-active')) {
        mobileNavToogle();
      }
    });
  });

  /**
   * Toggle mobile nav dropdowns
   */
  var navmenuDropdowns = document.querySelectorAll('.navmenu .toggle-dropdown');
  Array.prototype.forEach.call(navmenuDropdowns, function(navmenu) {
    navmenu.addEventListener('click', function(e) {
      e.preventDefault();
      this.parentNode.classList.toggle('active');
      this.parentNode.nextElementSibling.classList.toggle('dropdown-active');
      e.stopImmediatePropagation();
    });
  });

  /**
   * Preloader
   */
  var preloader = document.querySelector('#preloader');
  if (preloader) {
    window.addEventListener('load', function() {
      preloader.remove();
    });
  }

  /**
   * Scroll top button
   */
  var scrollTop = document.querySelector('.scroll-top');

  function toggleScrollTop() {
    if (scrollTop) {
      if (window.scrollY > 100) {
        scrollTop.classList.add('active');
      } else {
        scrollTop.classList.remove('active');
      }
    }
  }
  if (scrollTop) {
    scrollTop.addEventListener('click', function(e) {
      e.preventDefault();
      window.scrollTo({
        top: 0,
        behavior: 'smooth'
      });
    });
  }

  window.addEventListener('load', toggleScrollTop);
  document.addEventListener('scroll', toggleScrollTop);

  /**
   * Animation on scroll function and init
   */
  function aosInit() {
    if (window.AOS) {
      AOS.init({
        duration: 600,
        easing: 'ease-in-out',
        once: true,
        mirror: false
      });
    }
  }
  window.addEventListener('load', aosInit);

  /**
   * Initiate glightbox
   */
  if (window.GLightbox) {
    var glightbox = GLightbox({
      selector: '.glightbox'
    });
  }

  /**
   * Init isotope layout and filters
   */
  var isotopeItems = document.querySelectorAll('.isotope-layout');
  Array.prototype.forEach.call(isotopeItems, function(isotopeItem) {
    var layout = isotopeItem.getAttribute('data-layout') || 'masonry';
    var filter = isotopeItem.getAttribute('data-default-filter') || '*';
    var sort = isotopeItem.getAttribute('data-sort') || 'original-order';

    var initIsotope;
    imagesLoaded(isotopeItem.querySelector('.isotope-container'), function() {
      initIsotope = new Isotope(isotopeItem.querySelector('.isotope-container'), {
        itemSelector: '.isotope-item',
        layoutMode: layout,
        filter: filter,
        sortBy: sort
      });
    });

    var isotopeFilters = isotopeItem.querySelectorAll('.isotope-filters li');
    Array.prototype.forEach.call(isotopeFilters, function(filters) {
      filters.addEventListener('click', function() {
        isotopeItem.querySelector('.isotope-filters .filter-active').classList.remove('filter-active');
        this.classList.add('filter-active');
        initIsotope.arrange({
          filter: this.getAttribute('data-filter')
        });
        if (typeof aosInit === 'function') {
          aosInit();
        }
      });
    });

  });

  /**
   * Init swiper sliders
   */
  function initSwiper() {
    var swiperElements = document.querySelectorAll(".init-swiper");
    Array.prototype.forEach.call(swiperElements, function(swiperElement) {
      var config = JSON.parse(
        swiperElement.querySelector(".swiper-config").innerHTML.trim()
      );

      if (swiperElement.classList.contains("swiper-tab")) {
        initSwiperWithCustomPagination(swiperElement, config);
      } else {
        new Swiper(swiperElement, config);
      }
    });
  }

  window.addEventListener("load", initSwiper);

  /**
   * Correct scrolling position upon page load for URLs containing hash links.
   */
  window.addEventListener('load', function() {
    if (window.location.hash) {
      var section = document.querySelector(window.location.hash);
      if (section) {
        setTimeout(function() {
          var scrollMarginTop = getComputedStyle(section).scrollMarginTop;
          window.scrollTo({
            top: section.offsetTop - parseInt(scrollMarginTop, 10),
            behavior: 'smooth'
          });
        }, 100);
      }
    }
  });

  /**
   * Navmenu Scrollspy
   */
  var navmenulinks = document.querySelectorAll('.navmenu a');

  function navmenuScrollspy() {
    Array.prototype.forEach.call(navmenulinks, function(navmenulink) {
      if (!navmenulink.hash) return;
      var section = document.querySelector(navmenulink.hash);
      if (!section) return;
      var position = window.scrollY + 200;
      if (position >= section.offsetTop && position <= (section.offsetTop + section.offsetHeight)) {
        document.querySelectorAll('.navmenu a.active').forEach(function(link) {
          link.classList.remove('active');
        });
        navmenulink.classList.add('active');
      } else {
        navmenulink.classList.remove('active');
      }
    });
  }
  window.addEventListener('load', navmenuScrollspy);
  document.addEventListener('scroll', navmenuScrollspy);
  
	//Offer Slider
	document.addEventListener('DOMContentLoaded', function () {
    // Initialize Swiper
    var swiper = new Swiper('.swiper', {
      loop: true,
      speed: 600,
      autoplay: {
        delay: 5000,
      },
      slidesPerView: 'auto',
      pagination: {
        el: '.swiper-pagination',
        clickable: true,
      },
    });
  });
  
  
  //Facility 
  document.addEventListener('DOMContentLoaded', function () {
	    // Initialize Bootstrap tabs if they are not initialized automatically
	    var tabElements = document.querySelectorAll('.nav-tabs a[data-bs-toggle="tab"]');
	    tabElements.forEach(function (tabElement) {
	      new bootstrap.Tab(tabElement);
	    });

	    // Optional: handle tab change events for custom behavior
	    tabElements.forEach(function (tabElement) {
	      tabElement.addEventListener('shown.bs.tab', function (event) {
	        // event.target is the newly activated tab
	        // event.relatedTarget is the previous tab
	        console.log('Tab changed to:', event.target.id);
	      });
	    });
	  });
})();


//Menu Filter
document.addEventListener('DOMContentLoaded', function() {
    const filterButtons = document.querySelectorAll('.filter-btn');
    const menuItems = document.querySelectorAll('.menu-item');

    filterButtons.forEach(button => {
        button.addEventListener('click', function() {
            const category = this.getAttribute('data-category');

            filterButtons.forEach(btn => btn.classList.remove('active'));
            this.classList.add('active');

            menuItems.forEach(item => {
                if (category === 'all' || item.getAttribute('data-category') === category) {
                    item.classList.remove('d-none');
                } else {
                    item.classList.add('d-none');
                }
            });
        });
    });
});


//Cart Functionality
document.addEventListener('DOMContentLoaded', () => {
    const cartItems = {}; // Declare cartItems here for global access
    const cartCountElement = document.getElementById('cart-count');
    const cartTotalElement = document.getElementById('cart-total');
    const cartSubtotalElement = document.getElementById('cart-subtotal');
    const cartItemsElement = document.getElementById('cart-items');
    const cartIcon = document.getElementById('cart-icon'); // Cart icon element

    // Initially hide the cart icon
    cartIcon.style.display = 'none';

    // Add to Cart
    document.querySelectorAll('.add-to-cart').forEach(button => {
        button.addEventListener('click', (e) => {
            e.preventDefault();
            const menuID = button.getAttribute('data-id');
            const menuName = button.getAttribute('data-name');
            const menuPrice = parseFloat(button.getAttribute('data-price'));

            if (!cartItems[menuID]) {
                cartItems[menuID] = {
                    name: menuName,
                    price: menuPrice,
                    quantity: 1,
                    total: menuPrice
                };
            } else {
                cartItems[menuID].quantity++;
                cartItems[menuID].total = cartItems[menuID].quantity * menuPrice;
            }

            updateCart();
        });
    });

    // Update Cart
	function updateCart() {
	    cartItemsElement.innerHTML = '';
	    let total = 0;
	    let count = 0;
	    let subtotal = 0;

	    Object.keys(cartItems).forEach(menuID => {
	        const item = cartItems[menuID];
	        subtotal += item.total;
	        total += item.total;  
	        count += item.quantity;

	        const row = document.createElement('tr');
	        row.innerHTML = `
	            <td>${item.name}</td>
	            <td><input type="number" class="form-control quantity-input" data-id="${menuID}" value="${item.quantity}" min="1"></td>
	            <td>Rs. ${item.price}</td>
	            <td>Rs. ${item.total}</td>
	            <td><button class="btn btn-danger btn-sm remove-item" data-id="${menuID}">Remove</button></td>
	        `;
	        cartItemsElement.appendChild(row);
	    });

	    cartCountElement.textContent = count;
	    cartTotalElement.textContent = total.toFixed(2); 
	    cartSubtotalElement.textContent = subtotal.toFixed(2);

	    cartIcon.style.display = count > 0 ? 'block' : 'none';
	}

    // Event delegation for remove buttons and quantity inputs
    cartItemsElement.addEventListener('click', (e) => {
        if (e.target.classList.contains('remove-item')) {
            e.preventDefault();
            const menuID = e.target.getAttribute('data-id');
            delete cartItems[menuID];
            updateCart();
        }
    });

    cartItemsElement.addEventListener('change', (e) => {
        if (e.target.classList.contains('quantity-input')) {
            const menuID = e.target.getAttribute('data-id');
            const newQuantity = parseInt(e.target.value, 10);
            if (cartItems[menuID]) {
                cartItems[menuID].quantity = newQuantity;
                cartItems[menuID].total = cartItems[menuID].quantity * cartItems[menuID].price;
                updateCart();
            }
        }
    });

    // Handle checkout
	document.getElementById('checkout-button').addEventListener('click', () => {
	    const orderDetails = [];
	    Object.keys(cartItems).forEach(menuID => {
	        const item = cartItems[menuID];
	        orderDetails.push(`${item.name}(${item.quantity})`);
	    });

	    // Update the hidden fields
	    document.getElementById('order-details-input').value = orderDetails.join(', ');
	    document.getElementById('total-price-input').value = cartTotalElement.textContent.trim();  
	    document.getElementById('subtotal-input').value = cartSubtotalElement.textContent.trim();

	    checkUserAndProceed();
	});
	

    // Check login credentials and proceed
    function checkUserAndProceed() {
        const userID = document.getElementById('user-id-input').value;

        if (!userID || userID.trim() === "") {
            window.location.href = "customerLogin";
        } else {
            document.getElementById('cartForm').submit();
        }
    }
});


//Book a table for logged in customers
document.getElementById('reservationForm').addEventListener('submit', function(event) {
       const userID = document.getElementById('user-id-input').value;

       if (!userID || userID.trim() === "") {
           event.preventDefault();
           window.location.href = "customerLogin";
       }
   });
   
   document.addEventListener('DOMContentLoaded', function() {
       const reservationMessage = document.getElementById('reservation-message');
       if (reservationMessage) {
         setTimeout(() => {
           reservationMessage.style.display = 'none';
         }, 30000);
       }
	});




