import groovy.json.JsonSlurper

class OktaAPI {
    String tk = '<OKTA API TOKEN>'
    String url = '<OKTA INSTANCE URL>'
    
    public String getGroupId(String name){
        def String response_string= 
         Unirest.get(url+"/api/v1/groups?q="+name)
        .header('Authorization', tk)
        .asString().getBody()
        
        def jsonSlurper = new JsonSlurper()
        def json_object = jsonSlurper.parseText(response_string)
        return(json_object.id[0])
    }

    public String getUserId(String name){
        def String response_string= 
         Unirest.get(url+"/api/v1/users?q="+name)
        .header('Authorization', tk)
        .asString().getBody()
        
        def jsonSlurper = new JsonSlurper()
        def json_object = jsonSlurper.parseText(response_string)
        return(json_object.id[0])
    }

    public String userInGroup(String groupId,String user){
        def String response_string= 
         Unirest.get(url+"/api/v1/groups/"+groupId+"/users/")
        .header('Authorization', tk)
        .asString().getBody()
        
        def jsonSlurper = new JsonSlurper()
        def json_object = jsonSlurper.parseText(response_string)
        return(json_object.id[0])
    }

    public String addUser(String groupId,String userId){
        def String response_string= 
         Unirest.put(url+"/api/v1/groups/"+groupId+"/users/"+userId)
        .header('Authorization', tk)
        .asJson()
        
        return response_string
    }
}
