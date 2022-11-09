# usage: 
# to test locally script on namespace dev01 use
# . initshell.sh dev01
# so later on you may use aliases
# k == kubectl --namespace com.cr.projectname-dev01
# h == helm --namespace com.cr.projectname-dev01
namespace="com-crd-projectname-$1"
alias kc="kubectl"
alias k="kc --namespace $namespace"
alias h="helm --namespace $namespace"
