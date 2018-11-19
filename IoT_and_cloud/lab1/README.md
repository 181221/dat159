### Motion detecting system.

some thoughts:
I should have added a check after the user has entered the combinations.
Now it is made such that if a user presses the wrong button the system will enter the locked state immediately. This way it is easy to guess what is the right combination.
Secoudly I would like to implement the system with arduino`s attach interrupt. So when the user presses a button an interrupt will be called to CPU.
This way I do not need to know the previous state of the button and I can use a counter to know how many times the button has ben pressed. I can also
remove some of the loops. If I had implementet the attach interrupt handler there would be less and more readble code.
