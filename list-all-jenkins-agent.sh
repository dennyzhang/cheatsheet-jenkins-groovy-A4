#!/usr/bin/env bash
##  @copyright 2019 DennyZhang.com
## Licensed under MIT 
##   https://www.dennyzhang.com/wp-content/mit_license.txt
##
## File: list-all-jenkins-agent.sh
## Author : Denny <https://www.dennyzhang.com/contact>
## Description : List all trouble shooting traces for each Jenkins agent
## --
## Created : <2019-08-30>
## Updated: Time-stamp: <2019-08-30 14:41:22>
##-------------------------------------------------------------------
#!/usr/bin/env bash
# set -o errexit
set -o pipefail
set -o nounset

jenkins_url=${1?}
jenkins_credential=${2?}
vm_user=${3?}
vm_pass=${4?}
for agent_name in $(curl "$jenkins_url/computer/api/json" | jq -r .computer[].displayName | grep -v master); do
    ip=$(curl -u "$jenkins_credential" -d "script=println InetAddress.localHost.hostAddress" \
              "$jenkins_url/computer/$agent_name/scriptText" 2>/dev/null | sed -e 's/^[ \t]*//' | sed -e '/^$/d')
    echo "agent_name: $agent_name, ip: $ip"
    for command in "cat /proc/loadavg" "docker version | grep '^ Version:'" "df -h /var/lib"; do
        sshpass -p "$vm_pass" ssh -o LogLevel=error -o StrictHostKeyChecking=no "$vm_user@$ip" "$command"
    done
done

