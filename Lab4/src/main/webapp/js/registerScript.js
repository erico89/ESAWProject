var form = document.getElementById("myform");
var user = document.getElementById("nickname");
var email = document.getElementById("mail");
var pwd1 = document.getElementById("password");
var pwd2 = document.getElementById("confirmationPassword");
var birthDate = document.getElementById("birthdate");
var photo = document.getElementById("profilePhoto");

var checkPasswordValidity = function() {
	 if (pwd2.value !== pwd1.value ) {
		pwd2.setCustomValidity("Passwords must match!");
	} else {
		pwd2.setCustomValidity("");
	}
}

user.addEventListener("input", function (event) {
  if (user.validity.typeMismatch) {
    user.setCustomValidity("Invalid user name.");
  } else {
    user.setCustomValidity("");
  }
});

email.addEventListener("input", function (event) {
  if (email.validity.typeMismatch) {
    email.setCustomValidity("I am expecting an e-mail address!");
  } else {
    email.setCustomValidity("");
  }
});

pwd2.addEventListener("input", checkPasswordValidity, false);

birthDate.addEventListener("input", function (e) {
	if (isValidDate(birthDate.value)) {
		birthDate.setCustomValidity("");
	} else {
		console.log("error")
		birthDate.setCustomValidity("Invalid Date.");
	}	
});

photo.addEventListener("change", function (e) {
	isValidPhoto(this);
});

form.addEventListener("submit", function (event) {
	checkPasswordValidity();
	if (!this.checkValidity()) {
		this.reportValidity();
		event.preventDefault();
	} 
}, false);

// Validates that the input string is a valid date formatted as "mm/dd/yyyy"
function isValidDate(dateString)
{
    var birthDate = new Date(dateString);

    // First check for the pattern
    if(!birthDate)
        return false;
	
	// Check with current date
	var today = new Date();
	if (today < birthDate) 		return false;
	
	
    // Parse the date parts to integers
    var parts = dateString.split("-");
    var year = parseInt(parts[0], 10);
    var month = parseInt(parts[1], 10);
    var day = parseInt(parts[2], 10);

    // Check the ranges of month and year
    if(year < 1000 || year > 3000 || month == 0 || month > 12)
        return false;

    var monthLength = [ 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 ];

    // Adjust for leap years
    if(year % 400 == 0 || (year % 100 != 0 && year % 4 == 0))
        monthLength[1] = 29;

    // Check the range of the day
    return day > 0 && day <= monthLength[month - 1];
};

function isValidPhoto (input) 
{
	var URL = window.URL || window.webkitURL;
    var file = input.files[0];

    if (file) {
        var image = new Image();

        image.onload = function() {
            if (!this.width) {
                 photo.setCustomValidity("Invalid photo.");
            } else {
                 photo.setCustomValidity("");
			}
        };

        image.src = URL.createObjectURL(file);
    }
};