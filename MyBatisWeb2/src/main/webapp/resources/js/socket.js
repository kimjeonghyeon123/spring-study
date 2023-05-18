
		var socket = null
		$(document).ready(function() {
			connectWS()
		})	
		
		function connectWS() {
			var ws = new WebSocket("ws://localhost/korea/replyEcho")
			socket = ws
			
			ws.onopen = function() {
				console.log('Info: connection opened.')
			}
			
			ws.onmessage = function(event) {
				console.log("ReceiveMessage:", event.data+'\n')
			}
			
			ws.onclose = function (event){ 
				console.log('Info: connection closed') 
				//setTimeout(function(){connect()}, 1000)	
			}
			ws.onerror = function (err){ console.log('Error: ', err) }
		}