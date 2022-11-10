#TBD

By design HELM installers can't be customized using environment variables, and in the same time we would like to customize installation using environment variables.
To achieve that, we use predefined variables with version of images so that we may conver them to config file for HELM

Example:
To configure properties for HELM installer, define variables with definitions of required tags to be deployed:
```bash
export USERVICE_WEBAPI_IMAGE_TAG=latest
export USERVICE_DEAL_IMAGE_TAG=latest
export USERVICE_CALCULATION_IMAGE_TAG=latest
./makeconfig.sh
```
And later on apply on e.g. dev01
```bash
. initshell.sh dev01
h upgrade com.crd.projectname . -f config.yaml
```

##
## Target kubernetes configuration
- **microk8s with enabled ingress**. It is used in some places because ingress in microk8s has defined class and namespace
- **[cert-manager](https://cert-manager.io/)** installed with version 1.6

## Used artlcles
- https://cert-manager.io/docs/installation/helm/
- https://cert-manager.io/docs/configuration/acme/#creating-a-basic-acme-issuer
- https://github.com/jetstack/cert-manager/issues/1387
