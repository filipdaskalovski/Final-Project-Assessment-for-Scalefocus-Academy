# Final-Project-Assessment-for-Scalefocus-Academy

This Jenkins pipeline script has two stages.

In the "Verification" stage, it checks if the WordPress deployment exists in the "wp" namespace. If it does, it displays a message indicating that WordPress is already installed. Otherwise, it proceeds to the deployment stage.

In the "Deployment of Code" stage, it checks if the "wp" namespace exists and creates it if necessary. Then, it upgrades or installs the WordPress Helm chart using the specified release name, chart path, namespace, and values file. It also sets up port forwarding to access the WordPress service on local port 8416.

If any errors occur, it provides an error message and fails the pipeline execution.



I hope that my work was sufficient.

Thank you for your time.
