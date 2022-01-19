Application: Fotag!

platform: Mac
version of Java: 10.0.1

Description: 
This application allows users to display a set of images in both list and
grid view. This can be determined by the two buttons at the left top of the 
window. The default view is grid. There is a button called “upload”. 
Users can use this button to upload image to display in the window. The image’s 
last modified date and name will also be shown. Each image allows user to
rate. The reset button will set the rating to be 0. At the right top of the
window, there is a filter checkbox, if the checkbox is ticked, the image will
be filtered by the rating(e.g. clicking the 3-star search filter will show images with 3, 4, or 5 stars). Otherwise, all the loaded image will be
displayed. Furthermore, there will be an enlarged image in a separate window
if the image is clicked. When first launched, the application shows an empty window. After loading the image, it will save the list of images when exiting the application. On relaunch, the same images are automatically shown. This 
application also supports resizing in both Grid view and List view. 

This application uses the MVC pattern. 
  - two Models: ImageModel and ImageCollectionModel. ImageModel represents
a single image, including the metadata the path to the image, its last 
modified date, and user rating. ImageCollectionModel represents a collection
of ImageModel.
  - two Views: ImageView and ImageCollectionView. The ImageView is 
responsible for rendering a single ImageModel. The ImageCollectionView
 is the view for ImageCollectionModel objects.
  - One controller: Handle the mouse event.
