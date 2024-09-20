// Dropdown functionality for profile
const profileDropdownButton = document.getElementById('profileDropdownButton');
const profileDropdown = document.getElementById('profileDropdown');
chevronIcon = document.getElementById('chevronIcon');


profileDropdownButton.addEventListener('click', () => {
    profileDropdown.classList.toggle('hidden');

    if (profileDropdown.classList.contains('hidden')) {
        // Reverse animation (rotate back to 0deg)
        chevronIcon.classList.remove('flip-down');
        chevronIcon.classList.add('flip-up');
    } else {
        // Forward animation (rotate to 180deg)
        chevronIcon.classList.remove('flip-up');
        chevronIcon.classList.add('flip-down');
    }

});

const menuIcon = document.getElementById('menuIcon');
const navigation = document.getElementById('navigation');
const closeIcon = document.getElementById('closeIcon');
menuIcon.addEventListener('click', () => {
    navigation.classList.toggle('open'); // Toggle the 'open' class to show/hide the navigation
});
closeIcon.addEventListener('click', () => {
    navigation.classList.remove('open'); // Assuming you use the 'open' class to show the navigation
});
