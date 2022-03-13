# opencart-api
A Java library for the OpenCart (free) API

The free API is rather limited in scope but just enough there to programmatically create an order.
Within the order sub-API it appears that only add is implemented.

The API assumes a call sequence which matches the wizard style interaction of the web store for creation of an order, see example driver program.

Note to successfully add a product to the shopping cart it is necessary to have knowledge of the product ID and associated options, these may be gleaned by inspecting the page source when browsing a product of interest in the web store.

At the time of writing this is raw code only, sorry no JUnit test cases.

## Development & Test Environment

Compose the following Docker image provided by bitnami on DockerHub

[OpenCart packaged by Bitnami](https://hub.docker.com/r/bitnami/opencart)

Register at opencart.com and follow the instructions in the following link to generate the API key for your store and enable the API within your OpenCart instance.

[Signature hash does not match](https://webocreation.com/blog/signature-hash-not-match-opencart-solution/)

Login to your OpenCart on the /admin URL as user/bitnami and navigate to Users|API and add an API user including generation of the key for this user, see

[How to use Opencart API](https://www.youtube.com/watch?v=bc2govdEiuA)

The API username and key is then coded into the application.properties file for the driver program in this project.
