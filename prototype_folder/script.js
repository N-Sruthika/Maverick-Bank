document.querySelectorAll(".nav-item").forEach((item) => {
  item.addEventListener("mouseover", function () {
    document.querySelectorAll(".hover-content").forEach((box) => {
      box.style.display = "none"; // Hide other sections
    });
    let contentId = this.getAttribute("data-content");
    document.getElementById(contentId).style.display = "block";
  });

  item.addEventListener("mouseleave", function () {
    setTimeout(() => {
      document.getElementById(this.getAttribute("data-content")).style.display =
        "none";
    }, 2000);
  });
});

// EMI Calculator Function
function calculateEMI() {
  let loanAmount = document.getElementById("loanAmount").value;
  let tenure = document.getElementById("tenure").value;
  let interestRate = 8.85 / 1200; // Monthly interest rate

  if (loanAmount && tenure) {
    let emi =
      (loanAmount * interestRate * Math.pow(1 + interestRate, tenure)) /
      (Math.pow(1 + interestRate, tenure) - 1);
    document.getElementById(
      "emi-result"
    ).innerText = `Estimated EMI: ₹${emi.toFixed(2)}`;
  } else {
    document.getElementById("emi-result").innerText = "Enter valid details";
  }
}
