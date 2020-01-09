#!/usr/bin/env bash

URL="http://rsj.beijing.gov.cn/integralpublic/settlePerson/tablePage"
rows=100

echo "<html>" > name-list.html;
for (( pageNo=0; pageNo<=62; pageNo++ ))
do
    let page=${pageNo}*${rows};
    curl -d "name=&rows="${rows}"&page="${page} ${URL} -o "data-"${pageNo};
    cat "data-"${pageNo} >> name-list.html;
done
echo "</html>" >> name-list.html;
