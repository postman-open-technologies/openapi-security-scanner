FROM amazon/aws-lambda-java:11

COPY ./build/classes/kotlin/main ${LAMBDA_TASK_ROOT}
COPY ./build/dependency/* ${LAMBDA_TASK_ROOT}/lib/

RUN yum -y install curl && \
    curl -sL https://rpm.nodesource.com/setup_16.x | bash - && \
    yum install -y nodejs && \
    npm install -g newman

RUN mkdir ${LAMBDA_TASK_ROOT}/zap  && \
    mkdir -p ${LAMBDA_TASK_ROOT}/.ZAP/policies

COPY --from=owasp/zap2docker-bare /zap ${LAMBDA_TASK_ROOT}/zap
COPY --from=owasp/zap2docker-bare /home/zap/.ZAP/policies ${LAMBDA_TASK_ROOT}/.ZAP/policies/

# Uninstall unused add-ons
RUN ${LAMBDA_TASK_ROOT}/zap/zap.sh \
    -cmd \
    -dir ${LAMBDA_TASK_ROOT}/.ZAP \
    -addonuninstall domxss \
    -addonuninstall gettingStarted \
    -addonuninstall graaljs \
    -addonuninstall invoke \
    -addonuninstall formhandler \
    -addonuninstall graphql \
    -addonuninstall zest \
    -addonuninstall retest \
    -addonuninstall automation \
    -addonuninstall bruteforce \
    -addonuninstall onlineMenu \
    -addonuninstall openapi \
    -addonuninstall spiderAjax \
    -addonuninstall tips \
    -addonuninstall scripts \
    -addonuninstall selenium \
    -addonuninstall quickstart \
    -addonuninstall help \
    -addonuninstall retire \
    -addonuninstall soap \
    -addonuninstall saverawmessage \
    -addonuninstall savexmlmessage \
    -addonuninstall hud \
    -addonuninstall encoder \
    -addonuninstall importurls \
    -addonuninstall oast \
    -addonuninstall diff \
    -addonuninstall replacer \
    -addonuninstall callhome \
    -addonuninstall fuzz \
    -addonuninstall reveal

# Update add-ons
RUN ${LAMBDA_TASK_ROOT}/zap/zap.sh -dir ${LAMBDA_TASK_ROOT}/.ZAP -cmd -addonupdate

CMD [ "com.postman.securityscanner.infrastructure.aws.lambda.Handler::handleRequest" ]
# Inside container, run Zap with: ${LAMBDA_TASK_ROOT}/zap/zap.sh -dir ${LAMBDA_TASK_ROOT}/.ZAP -daemon -config "api.addrs.addr.name=.*" -config api.addrs.addr.regex=true -config api.disablekey=true
