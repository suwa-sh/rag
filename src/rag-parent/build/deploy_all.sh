#!/bin/sh
#================================================================================
#================================================================================
#--------------------------------------------------
# 変数定義
#--------------------------------------------------
DIR_SCRIPT=`dirname $0`
DIR_LOG=${DIR_SCRIPT}/log
FILE_LOG=`basename $0 .sh`.log
PATH_LOG=${DIR_LOG}/${FILE_LOG}

EXEC_CMD="mvn clean site cobertura:cobertura deploy"
EXEC_CMD_PARENT="mvn clean deploy"
#EXEC_CMD="mvn clean jacoco:prepare-agent test jacoco:report site:site install"


#--------------------------------------------------
# 事前処理
#--------------------------------------------------
cd ${DIR_SCRIPT}

if [ ! -e ${DIR_LOG} ]; then
    mkdir ${DIR_LOG}
fi

#--------------------------------------------------
# 主処理
#--------------------------------------------------
cd ..
echo [parent] ${EXEC_CMD_PARENT} | tee -a ${PATH_LOG}
${EXEC_CMD_PARENT} >> ${PATH_LOG} 2>&1
if [ $? -ne 0 ]; then
    echo build failure ${EXEC_CMD_PARENT} | tee -a ${PATH_LOG}
    exit 1
fi

echo [infra layer] ${EXEC_CMD} | tee -a ${PATH_LOG}
cd ../rag-infra
${EXEC_CMD} >> ${PATH_LOG} 2>&1
if [ $? -ne 0 ]; then
    echo build failure ${EXEC_CMD} | tee -a ${PATH_LOG}
    exit 1
fi

echo [recognize layer] ${EXEC_CMD} | tee -a ${PATH_LOG}
cd ../rag-recognize
${EXEC_CMD} >> ${PATH_LOG}  2>&1
if [ $? -ne 0 ]; then
    echo build failure ${EXEC_CMD} | tee -a ${PATH_LOG}
    exit 1
fi

echo [service layer] ${EXEC_CMD} | tee -a ${PATH_LOG}
cd ../rag-service
${EXEC_CMD} >> ${PATH_LOG} 2>&1
if [ $? -ne 0 ]; then
    echo build failure ${EXEC_CMD} | tee -a ${PATH_LOG}
    exit 1
fi

echo [application layer] ${EXEC_CMD} | tee -a ${PATH_LOG}
cd ../rag-application
${EXEC_CMD} >> ${PATH_LOG} 2>&1
if [ $? -ne 0 ]; then
    echo build failure ${EXEC_CMD} | tee -a ${PATH_LOG}
    exit 1
fi

echo [batch layer] ${EXEC_CMD} | tee -a ${PATH_LOG}
cd ../rag-batch
${EXEC_CMD} >> ${PATH_LOG} 2>&1
if [ $? -ne 0 ]; then
    echo build failure ${EXEC_CMD} | tee -a ${PATH_LOG}
    exit 1
fi


#--------------------------------------------------
# 事後処理
#--------------------------------------------------
exit 0
