# Factor Examples

These vocabulary examples are supplemental to ["An Introduction to Factor"](../Factor.pdf).

## Vocabulary Usage

The example files and folders provided in this directory are Factor vocabularies.
Factor vocabularies should typically be placed within the Factor working directory.
By default this is the `<factor>\work` subfolder located within the Factor installation path. 

## Example 1 - Private Helper Words

The `caesar` example demonstrates usage of private helper words.

To test the `caesar` vocabulary start a new Factor Listener session and submit 
`USE: caesar` to the listener. If successful the listener will continue with no 
additional prompt. If an error occurs check to make sure that the `caesar` 
vocabulary folder is within your working path.

Once accessed, the `caesar` word may be used by supplying the proper arguments 
to it upon the stack. For example, submitting `"abcdef" 6 caesar` should result in 
`"ghijkl"` on the stack.

## Example 2 - Class Constructors & Words

The `novel` example demonstrates a simple class constructor and a method that
operates on the class.

To test the `novel` vocabulary start a new Factor Listener session and submit 
`USE: novel` to the listener. If successful the listener will continue with no 
additional prompt. If an error occurs check to make sure that the `novel` 
vocabulary folder is within your working path.

Once accessed, the `novel` constructor word may be used by supplying the proper 
arguments to it upon the stack. The `<novel>` word expects strings representing a 
title, author, genre, publisher, and integers representing a year of publication as 
well as an identification number, as in the following example: `"The Lion, the Witch, and the Wardrobe" "C. S. Lewis" "Fantasy" "Geoffrey Bles" 
1952 1 <novel>`.

When executed the `<novel>` constructor will leave a `novel` on the stack. To print
the details to the listener terminal use the `book-print` word.

## Example 3 - Mixin Classes & Generic Words

The `library` example showcases the `mixin` feature to implement class unions, as well as
the way to define class-specific implementations of functions.

To test the `library` vocabulary start a new Factor Listener session and submit 
`USE: library` to the listener. If successful the listener will continue with no 
additional prompt. If an error occurs check to make sure that the `library` 
vocabulary folder is within your working path.

Once accessed, the `library` provides definitions for the `novel`, `article`, and
`textbook` classes. These classes have similar arguments to the `novel` mentioned above.
Specifically, the `article` changes the genre field to discipline and adds journal and volume
fields. The `textbook` field changes the genre field to a subject.

When executed the `book-print` word will operate on any instance of a `library` object.
