import React from 'react';
import { Link } from 'react-router-dom';
import './Home.css';

function Home() {
  return (
    <div className="body">
      <header className="topbar">
        <div className="logoContainer">
          <img src={'/kotak-logo.png'} alt="Maverick Bank" />
        </div>
        <nav className="navLinks">
          <a href="#" className="navLink">Personal</a>
          <a href="#" className="navLink">Business</a>
          <a href="#" className="navLink">Corporate</a>
          <a href="#" className="navLink">NRI</a>
        </nav>
      </header>

      <section className="hero">
        <div className="heroContent">
          <h1>Welcome to Maverick Bank Net Banking</h1>
          <p>Experience secure and convenient banking at your fingertips. Access your accounts, make payments, and manage your finances 24/7.</p>
          <div className="heroActions">
            <Link to="/customer/signup" className="btn btnSecondary">Sign UP</Link>
            <Link to="/login" className="btn btnPrimary">Login to Net Banking</Link>
          </div>
        </div>
        <div className="heroImage">
          <img src='/banking-hero.jpg' alt="Secure Online Banking" />
        </div>
      </section>

      <section className="features">
        <h2 className="sectionTitle">Why Choose Maverick Bank Net Banking?</h2>
        <div className="featuresGrid">
          <div className="featureCard">
            <div className="featureIcon">🔒</div>
            <h3>Secure Banking</h3>
            <p>Bank with confidence using our state-of-the-art security features and multi-factor authentication.</p>
          </div>
          <div className="featureCard">
            <div className="featureIcon">⚡</div>
            <h3>Quick Transfers</h3>
            <p>Make instant fund transfers to any bank account with IMPS, NEFT, and RTGS facilities.</p>
          </div>
          <div className="featureCard">
            <div className="featureIcon">📱</div>
            <h3>24/7 Access</h3>
            <p>Access your account anytime, anywhere through our secure and responsive platform.</p>
          </div>
        </div>
      </section>

      <footer className="footer">
        <div className="footerGrid">
          <div className="footerSection">
            <h3>Quick Links</h3>
            <ul className="footerLinks">
              <li><a href="#accounts">Accounts</a></li>
              <li><a href="#deposits">Deposits</a></li>
              <li><a href="#cards">Cards</a></li>
              <li><a href="#loans">Loans</a></li>
            </ul>
          </div>
          <div className="footerSection">
            <h3>Help & Support</h3>
            <ul className="footerLinks">
              <li><a href="#faq">FAQs</a></li>
              <li><a href="#security">Security</a></li>
              <li><a href="#contact">Contact Us</a></li>
              <li><a href="#locate">Locate Us</a></li>
            </ul>
          </div>
          <div className="footerSection">
            <h3>About Us</h3>
            <ul className="footerLinks">
              <li><a href="#about">About Maverick Bank</a></li>
              <li><a href="#careers">Careers</a></li>
              <li><a href="#news">News</a></li>
              <li><a href="#investors">Investors</a></li>
            </ul>
          </div>
          <div className="footerSection">
            <h3>Legal</h3>
            <ul className="footerLinks">
              <li><a href="#privacy">Privacy Policy</a></li>
              <li><a href="#terms">Terms & Conditions</a></li>
              <li><a href="#disclaimer">Disclaimer</a></li>
              <li><a href="#grievance">Grievance Redressal</a></li>
            </ul>
          </div>
        </div>
       
      </footer>
    </div>
  );
}

export default Home;
