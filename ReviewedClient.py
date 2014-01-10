import httplib
import base64

HOST="localhost"
PORT=8080
IS_HTTPS="false";

class BeenReviewedClient(object):
    def __init__(self):
        object.__init__(self)

    def getConnection(self):
        if (IS_HTTPS == "true"):
            return httplib.HTTPSConnection(HOST, QA_PORT)
        else:
            return httplib.HTTPConnection(HOST, PORT)

    def saveSummary(self):
        request = '{"name":"The Hobbit I","type":"MOVIE","desc":"Part one of the Hobbit trilogy!"}'
        conn = self.getConnection() 
        print 'Sending saveSummary'
        conn.request("POST", "/rest/summary",
                        request,
                        {
                            "Content-type" : "application/json"
                        }
                    ); 

        response = conn.getresponse()
        body = response.read()
        print response.status, response.reason, response.getheaders(), body;

    def saveComment(self):
        request = '{"text":"Fantastic movie!!!","user":"gringo","rating":"FIVE"}'
        conn = self.getConnection() 
        print 'Sending saveComment'
        conn.request("POST", "/rest/summary/1389037928995/comment",
                        request,
                        {
                            "Content-type" : "application/json"
                        }
                    ); 

        response = conn.getresponse()
        body = response.read()
        print response.status, response.reason, response.getheaders(), body;

    def likeComment(self):
        request = ''
        conn = self.getConnection() 
        print 'Sending likeComment'
        conn.request("PUT", "/rest/summary/1389037928995/comment/1389037982013/like?username=gringo",
                        request,
                        {
                            "Content-type" : "application/json"
                        }
                    ); 

        response = conn.getresponse()
        body = response.read()
        print response.status, response.reason, response.getheaders(), body;

    def saveUser(self):
        request = '{"username":"gringo","email":"gringo@here.com"}'
        conn = self.getConnection() 
        print 'Sending saveUser'
        conn.request("POST", "/rest/user",
                        request,
                        {
                            "Content-type" : "application/json"
                        }
                    ); 

        response = conn.getresponse()
        body = response.read()
        print response.status, response.reason, response.getheaders(), body;

client = BeenReviewedClient()
#client.saveSummary()
#client.saveComment()
#client.likeComment()
#client.saveUser()