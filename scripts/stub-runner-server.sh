stubrunnerpath=~/git/spring-cloud-contract-workshop/exercise/stub-runner
# DOWNLOADED
java -jar ${stubrunnerpath}/stub-runner.jar  --server.port=8085 --stubrunner.stubsMode="LOCAL" --stubrunner.ids="de.fabiankrueger.scc:cashier:1.0-SNAPSHOT:stubs:8083"

# REMOTE
#java -jar ../stub-runner-server/target/stub-runner-server-1.0-SNAPSHOT.jar \
#    --stubrunner.stubsMode="REMOTE" \
#    --stubrunner.ids="de.fabiankrueger.scc:cashier:*:stubs:8083" \
#    --stubrunner.repositoryRoot="git://file:///Users/fkrueger/git/spring-cloud-contract-workshop/exercise/contracts-repo/"

# LOCAL
#java -jar ../stub-runner-server/target/stub-runner-server-1.0-SNAPSHOT.jar \
#    --stubrunner.stubsMode="LOCAL" \
#    --stubrunner.ids="de.fabiankrueger.scc:cashier:1.0-SNAPSHOT:stubs:8083"