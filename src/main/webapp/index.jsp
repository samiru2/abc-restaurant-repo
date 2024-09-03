<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="utf-8">
  <meta content="width=device-width, initial-scale=1.0" name="viewport">
  <title>ABC Restaurant</title>
  <meta name="description" content="">
  <meta name="keywords" content="">

  <!-- Favicons -->
  <link href="assets/img/favicon.png" rel="icon">
  <link href="assets/img/apple-touch-icon.png" rel="apple-touch-icon">

  <!-- Fonts -->
  <link href="https://fonts.googleapis.com" rel="preconnect">
  <link href="https://fonts.gstatic.com" rel="preconnect" crossorigin>
  <link href="https://fonts.googleapis.com/css2?family=Roboto:ital,wght@0,100;0,300;0,400;0,500;0,700;0,900;1,100;1,300;1,400;1,500;1,700;1,900&family=Poppins:ital,wght@0,100;0,200;0,300;0,400;0,500;0,600;0,700;0,800;0,900;1,100;1,200;1,300;1,400;1,500;1,600;1,700;1,800;1,900&family=Playfair+Display:ital,wght@0,400;0,500;0,600;0,700;0,800;0,900;1,400;1,500;1,600;1,700;1,800;1,900&display=swap" rel="stylesheet">

  <!-- Vendor CSS Files -->
  <link href="assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
  <link href="assets/vendor/bootstrap-icons/bootstrap-icons.css" rel="stylesheet">
  <link href="assets/vendor/aos/aos.css" rel="stylesheet">
  <link href="assets/vendor/glightbox/css/glightbox.min.css" rel="stylesheet">
  <link href="assets/vendor/swiper/swiper-bundle.min.css" rel="stylesheet">
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/swiper@9/swiper-bundle.min.css">


  <!-- Main CSS File -->
  <link href="assets/css/main.css" rel="stylesheet">

</head>

<body class="index-page">

<header id="header" class="header fixed-top">
        <div class="branding d-flex align-items-center">
            <div class="container position-relative d-flex align-items-center justify-content-between">
                <a href="index.jsp" class="logo d-flex align-items-center me-auto me-xl-0">
                    <h1 class="sitename">ABC Restaurant</h1>
                </a>

                <nav id="navmenu" class="navmenu">
                    <ul>
                        <li><a href="#hero" class="active">Home<br></a></li>
                        <li><a href="#about">About</a></li>
                        <li><a href="#menu">Menu</a></li>
                        <li><a href="#specials">Facilities</a></li>
                        <li><a href="#promotions">Promotions</a></li>
                        <li><a href="#gallery">Gallery</a></li>
                        <li><a href="#contact">Contact</a></li>
                        <li id="cart-icon" class="hidden-cart">
                            <a href="#" data-bs-toggle="modal" data-bs-target="#cartModal">
                                <i class="bi bi-cart-fill" style="font-size: 1.5rem; color: green;"></i>
                                <span class="badge bg-danger" id="cart-count">0</span>
                            </a>
                        </li>
                    </ul>
                    <i class="mobile-nav-toggle d-xl-none bi bi-list"></i>
                </nav>

  		<c:choose>
    <c:when test="${not empty loggedUser}">
        <c:choose>
            <c:when test="${userRole == 'customer'}">
                <a class="btn-book-a-table d-none d-xl-block" href="index.jsp">
                    Welcome <c:out value="${loggedUser.username}"/>
                </a>
            </c:when>
            <c:otherwise>
                <a class="btn-book-a-table d-none d-xl-block" href="index.jsp">
                    Not a customer
                </a>
            </c:otherwise>
        </c:choose>
        <a class="btn-book-a-table d-none d-xl-block" href="logout" title="Logout">
            <i class="bi bi-box-arrow-right"></i>
        </a>
    </c:when>
    <c:otherwise>
        <a class="btn-book-a-table d-none d-xl-block" href="customerLogin">My Account</a>
    </c:otherwise>
	</c:choose>
            </div>
        </div>
    </header>


  <main class="main">

    <!-- Hero Section -->
    <section id="hero" class="hero section dark-background">

      <img src="assets/img/topfood-bg.jpg" alt="" data-aos="fade-in">

      <div class="container">
        <div class="row">
          <div class="col-lg-12 d-flex flex-column align-items-center align-items-lg-center">
            <h2 data-aos="fade-up" data-aos-delay="100">Welcome to <span>ABC Restaurant</span></h2>
            <p data-aos="fade-up" data-aos-delay="200">Sri Lanka's Popular Restaurant Chain</p>
            <div class="d-flex mt-4" data-aos="fade-up" data-aos-delay="300">
              <a href="#menu" class="cta-btn">Our Menu</a>
              <a href="tablebooking.html" class="cta-btn">Book a Table</a>
            </div>
          </div>
        </div>
      </div>

    </section><!-- /Hero Section -->

    <!-- Promotion Section -->
    <section id="promotions" class="events section">
    <div class="container section-title" data-aos="fade-up">
        <p style="text-align: center;">Offers</p>
    </div>
    <img class="slider-bg" src="assets/img/bg-promotion.jpg" alt="" data-aos="fade-in">

    <div class="container">
        <div class="swiper init-swiper" data-aos="fade-up" data-aos-delay="100">
            <script type="application/json" class="swiper-config">
                {
                    "loop": true,
                    "speed": 600,
                    "autoplay": {
                        "delay": 5000
                    },
                    "slidesPerView": "auto",
                    "pagination": {
                        "el": ".swiper-pagination",
                        "type": "bullets",
                        "clickable": true
                    }
                }
            </script>
            <div class="swiper-wrapper">
                <!-- Dynamic content for offers -->
                <c:forEach var="offer" items="${offers}">
                    <div class="swiper-slide">
                        <div class="row gy-4 event-item">
                            <div class="col-lg-6">
                                <img src="${offer.image}" class="img-fluid" alt="${offer.title}" style="width: 40%; object-fit: cover;">
                            </div>
                            <div class="col-lg-6 pt-4 pt-lg-0 content">
                                <h3>${offer.title}</h3>
                                <div class="price">
                                    <p><span>$${offer.price}</span></p>
                                </div>
                                <p class="fst-italic">
                                    ${offer.description}
                                </p>
                            </div>
                        </div>
                    </div><!-- End Slider item -->
                </c:forEach>
                <!-- End dynamic content for offers -->
            </div>
            <div class="swiper-pagination"></div>
        </div>
    </div>
</section><!-- /Promotion Section -->

    
    <!-- Menu Section -->
	<section id="menu">
    <div class="container section-title" style="background: rgba(41, 38, 31, 0.8); padding: 20px; border-radius: 8px; color: white;" data-aos="fade-up">
        <p class="menu-description">Discover Our Flavourful Menu</p>
        <div class="row">
            <nav class="navbar navbar-dark">
                <div class="container-fluid">
                    <form class="d-flex" role="search">
                        <input class="form-control me-2 search-input" type="search" placeholder="Search" aria-label="Search">
                        <button class="btn btn-outline-success" type="button" id="search-button">Search</button>
                    </form>
                </div>
            </nav>
        </div>
    </div>
    <div class="container" style="background: rgba(41, 38, 31, 0.8); padding: 20px; border-radius: 8px; color: white;" data-aos="fade-up" data-aos-delay="100">
        <div class="row mb-4 text-center">
            <div class="col">
                <button class="btn btn-success filter-btn" data-category="all">All</button>
                <button class="btn btn-success filter-btn" data-category="Snack">Snack</button>
                <button class="btn btn-success filter-btn" data-category="Main Course">Main Course</button>
                <button class="btn btn-success filter-btn" data-category="Drinks">Drinks</button>
                <button class="btn btn-success filter-btn" data-category="Dessert">Dessert</button>
            </div>
        </div>
        <div class="row" id="menu-items">
            <c:forEach var="menu" items="${menus}">
                <div class="col-lg-3 col-md-4 col-sm-6 d-flex align-items-stretch menu-item" data-category="${menu.category}">
                    <div class="card mb-4 shadow-sm">
                        <img src="${menu.image}" class="card-img-top" alt="${menu.name}" style="max-height: 200px; object-fit: cover;">
                        <div class="card-body text-center">
                            <h5 class="card-title">${menu.name}</h5>
                            <p class="card-text">${menu.description}</p>
                            <p class="card-price">Rs. ${menu.price}</p>
                            <p class="card-category" style="display: none;">${menu.category}</p>
                            <a href="#" class="btn btn-success add-to-cart" data-id="${menu.menuId}" data-name="${menu.name}" data-price="${menu.price}">Add to Cart</a>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</section>
<!-- /Menu Section -->


    <!-- About Section -->
    <section id="about" class="about section">
      <div class="container" data-aos="fade-up" data-aos-delay="100">
        <div class="text-center mb-4">
          <h3>About Us</h3>
          <img src="assets/img/about.jpg" class="img-fluid about-img" alt="About Us Image">
        </div>
        <div class="text-container">
          
          <p class="fst-italic">
            Welcome to ABC Restaurant, where culinary passion meets exceptional hospitality. We are dedicated to crafting memorable dining experiences in the heart of Kandy City.
          </p>
          <ul>
            <li><span>Our menu features a blend of classic favorites all designed to showcase the finest flavors and seasonal ingredients.</span></li>
            <li><span>Led by Chef Floyd, a culinary expert with 12 years of experience</span></li>
            <li><span>Our friendly and attentive staff are here to ensure you have a seamless dining experience.</span></li>
          </ul>
          <p>
            Whether it is a special occasion or a casual meal, we invite you to experience the excellence of ABC Restaurant. We look forward to serving you soon!
          </p>
        </div>
      </div>
    </section><!-- /About Section -->

   <!-- Facility Section -->
<section id="specials" class="specials section">

  <!-- Section Title -->
  <div class="container section-title" data-aos="fade-up" style="background: rgba(41, 38, 31, 0.8);">
    <p style="text-align: center;">Our Facilities</p>
  </div><!-- End Section Title -->

  <div class="container" data-aos="fade-up" data-aos-delay="100" style="background: rgba(41, 38, 31, 0.8);">

    <div class="row">
      <!-- Facility Navigation -->
      <div class="col-lg-3">
        <ul class="nav nav-tabs flex-column">
          <c:forEach var="facility" items="${facilities}" varStatus="status">
            <li class="nav-item">
              <a class="nav-link ${status.index == 0 ? 'active show' : ''}" data-bs-toggle="tab" href="#facility-tab-${status.index}">
                ${facility.name}
              </a>
            </li>
          </c:forEach>
        </ul>
      </div><!-- End Facility Navigation -->

      <!-- Facility Details -->
      <div class="col-lg-9 mt-4 mt-lg-0">
        <div class="tab-content">
          <c:forEach var="facility" items="${facilities}" varStatus="status">
            <div class="tab-pane ${status.index == 0 ? 'active show' : ''}" id="facility-tab-${status.index}">
              <div class="row">
                <div class="col-lg-8 details order-2 order-lg-1">
                  <h3>${facility.name}</h3>
                  <p class="fst-italic">${facility.description}</p>
                </div>
                <div class="col-lg-4 text-center order-1 order-lg-2">
                  <img src="${facility.image}" alt="${facility.name}" class="img-fluid">
                </div>
              </div>
            </div>
          </c:forEach>
        </div>
      </div><!-- End Facility Details -->
    </div>

  </div>

</section><!-- /Facility Section -->

    

    <!-- Gallery Section -->
<section id="gallery" class="gallery section">
    <!-- Section Title -->
    <div class="container section-title" data-aos="fade-up">
        <p>Exotic Visuals at ABC Restaurant</p>
    </div><!-- End Section Title -->

    <div class="container-fluid" data-aos="fade-up" data-aos-delay="100">
        <div class="row g-0">
            <c:forEach var="gallery" items="${galleries}">
                <div class="col-lg-3 col-md-4">
                    <div class="gallery-item" style="position: relative; width: 100%; padding-top: 75%; overflow: hidden;">
                        <a href="${gallery.image}" class="glightbox" data-gallery="images-gallery">
                            <img src="${gallery.image}" alt="Gallery Image" class="img-fluid" style="position: absolute; top: 0; left: 0; width: 100%; height: 100%; object-fit: cover; transition: all ease-in-out 0.4s;">
                        </a>
                    </div>
                </div><!-- End Gallery Item -->
            </c:forEach>
        </div>
    </div>
</section><!-- End Gallery Section -->

<!-- Book A Table Section -->
<section id="book-a-table" class="book-a-table section" style="background: rgba(41, 38, 31, 0.8); padding: 20px; border-radius: 8px; color: white;">

  <!-- Section Title -->
  <div class="container section-title" data-aos="fade-up">
    <p style="text-align: center;">Book a Table</p>
  </div><!-- End Section Title -->

  <div class="container" data-aos="fade-up" data-aos-delay="100">

    <!-- Display Success or Error Messages -->
    <c:if test="${not empty successMessage}">
      <div class="alert alert-success" role="alert" id="reservation-message">
        ${successMessage}
      </div>
    </c:if>
    <c:if test="${not empty errorMessage}">
      <div class="alert alert-danger" role="alert" id="reservation-message">
        ${errorMessage}
      </div>
    </c:if>

    <form id="reservationForm" action="reservation?action=add" method="post" role="form" class="php-email-form">
      <!-- Hidden User ID Input -->
      <input type="hidden" name="userID" id="user-id-input" value="${userID}">
      
      <div class="row gy-4">
        <div class="col-lg-4 col-md-6">
          <input type="date" name="date" class="form-control" id="date" placeholder="Date" required>
        </div>
        <div class="col-lg-4 col-md-6">
          <input type="time" class="form-control" name="time" id="time" placeholder="Time" required>
        </div>
        <div class="col-lg-4 col-md-6">
          <input type="number" name="numberOfPeople" class="form-control" id="numberOfPeople" placeholder="# of people" required>
        </div>
        <div class="col-lg-4 col-md-6" style="display: none;">
          <input type="text" name="status" class="form-control" id="status" placeholder="Status" value="pending" readonly>
        </div>
      </div>

      <div class="form-group mt-3">
        <textarea class="form-control" name="message" rows="5" placeholder="Message"></textarea>
      </div>

      <div class="text-center mt-3">
        <button type="submit">Book a Table</button>
      </div>
    </form><!-- End Reservation Form -->

  </div>

</section><!-- /Book A Table Section -->



    <!-- Contact Section -->
    <section id="contact" class="contact section">

      <!-- Section Title -->
      <div class="container section-title" data-aos="fade-up">
        <p>Contact Us</p>
      </div><!-- End Section Title -->

      <div class="container" data-aos="fade-up" data-aos-delay="100">
        <div class="row gy-4">
          <div class="col-lg-4">
            <div class="info-item d-flex" data-aos="fade-up" data-aos-delay="300">
              <i class="bi bi-geo-alt flex-shrink-0"></i>
              <div>
                <h3>Location</h3>
                <p>432 DS Senanayake Street Kandy, Sri Lanka</p>
              </div>
            </div><!-- End Info Item -->

            <div class="info-item d-flex" data-aos="fade-up" data-aos-delay="400">
              <i class="bi bi-telephone flex-shrink-0"></i>
              <div>
                <h3>Open Hours</h3>
                <p>Monday-Saturday:<br>8:00 AM - 10:00 PM</p>
              </div>
            </div><!-- End Info Item -->

            <div class="info-item d-flex" data-aos="fade-up" data-aos-delay="400">
              <i class="bi bi-telephone flex-shrink-0"></i>
              <div>
                <h3>Call Us</h3>
                <p>+94 0202 58207</p>
              </div>
            </div><!-- End Info Item -->

            <div class="info-item d-flex" data-aos="fade-up" data-aos-delay="500">
              <i class="bi bi-envelope flex-shrink-0"></i>
              <div>
                <h3>Email Us</h3>
                <p>ABCRestaurant@gmail.com</p>
              </div>
            </div><!-- End Info Item -->

          </div>

          <div class="col-lg-8">
            <form action="forms/contact.php" method="post" class="php-email-form" data-aos="fade-up" data-aos-delay="200">
              <div class="row gy-4">

                <div class="col-md-6">
                  <input type="text" name="name" class="form-control" placeholder="Your Name" required="">
                </div>

                <div class="col-md-6 ">
                  <input type="email" class="form-control" name="email" placeholder="Your Email" required="">
                </div>

                <div class="col-md-12">
                  <input type="text" class="form-control" name="subject" placeholder="Subject" required="">
                </div>

                <div class="col-md-12">
                  <textarea class="form-control" name="message" rows="6" placeholder="Message" required=""></textarea>
                </div>

                <div class="col-md-12 text-center">
                  <div class="loading">Loading</div>
                  <div class="error-message"></div>
                  <div class="sent-message">Your message has been sent. Thank you!</div>

                  <button type="submit">Send Message</button>
                </div>

              </div>
            </form>
          </div><!-- End Contact Form -->

        </div>

      </div>

    </section><!-- /Contact Section -->

  </main>

  <footer id="footer" class="footer">
  <div class="container copyright text-center mt-4 d-flex flex-column align-items-center">
          <span class="sitename">ABC Restaurant</span>
          <p>432 DS Senanayake Street</p>
          <p>Kandy, Sri Lanka</p>
          <p class="mt-3"><strong>Phone:</strong> <span>+94 0202 58207</span></p>
          <p><strong>Email:</strong> <span>ABCrestaurant@gmail.com</span></p>
      </div>
  <div class="container copyright text-center mt-4 d-flex flex-column align-items-center">
    <p><span>Copyright</span> <strong class="px-1 sitename">A B C Restaurant</strong> <span>All Rights Reserved</span></p>
    <div class="credits">
      Designed by <a href="#">SAMIRU</a>
    </div>
  </div>
</footer>

  <!-- Scroll Top -->
  <a href="#" id="scroll-top" class="scroll-top d-flex align-items-center justify-content-center"><i class="bi bi-arrow-up-short"></i></a>

  <!-- Preloader -->
  <div id="preloader"></div>

  <!-- Vendor JS Files -->
  <script src="assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
  <script src="assets/vendor/php-email-form/validate.js"></script>
  <script src="assets/vendor/aos/aos.js"></script>
  <script src="assets/vendor/glightbox/js/glightbox.min.js"></script>
  <script src="assets/vendor/imagesloaded/imagesloaded.pkgd.min.js"></script>
  <script src="assets/vendor/isotope-layout/isotope.pkgd.min.js"></script>
  <script src="assets/vendor/swiper/swiper-bundle.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/swiper@9/swiper-bundle.min.js"></script>
  <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"></script>

  <!-- Main JS File -->
  <script src="assets/js/main.js"></script>
  
	<!-- Cart Modal -->
<div class="modal fade" id="cartModal" tabindex="-1" aria-labelledby="cartModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="cartModalLabel">Your Cart</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <form id="cartForm" action="index" method="post">
                <div class="modal-body">
                    <table class="table table-hover">
                        <thead>
                            <tr>
                                <th scope="col">Item</th>
                                <th scope="col">Quantity</th>
                                <th scope="col">Price</th>
                                <th scope="col">Subtotal</th>
                                <th scope="col">Action</th>
                            </tr>
                        </thead>
                        <tbody id="cart-items">
                        </tbody>
                    </table>
                    <div class="text-end">
                        <strong style="color: #3fc060;">Total Price: Rs. <span id="cart-subtotal">0</span></strong>
                        <br>
                        <strong>Total: Rs. <span id="cart-total">0</span></strong>
                    </div>
                    <input type="hidden" name="userID" id="user-id-input" value="${userID}">
                    <input type="hidden" name="orderDetails" id="order-details-input">
                    <input type="hidden" name="totalPrice" id="total-price-input">
                    <input type="hidden" name="subTotal" id="subtotal-input">
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Continue Shopping</button>
                    <button type="button" class="btn btn-success" id="checkout-button">Proceed to Checkout</button>
                </div>
            </form>
        </div>
    </div>
</div>


</body>

</html>