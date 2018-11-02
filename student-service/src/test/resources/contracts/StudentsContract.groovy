org.springframework.cloud.contract.spec.Contract.make {
	request {
	  method 'GET'
	  url '/students/1'	  	  
	}
  response {
	status 200	
	body("""
    [{
      "name":"Scott",
      "schoolId":1
    },
	{
	   "name":"Tiger",
	   "schoolId":1
    }]
    """)
   }
  }
  