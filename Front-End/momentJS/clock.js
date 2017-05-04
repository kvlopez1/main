	var Phoenix, London, Tokyo;
    function start(){
    	renderInternational();
        renderTime(); 
        moveSecondHands();
        setUpMinuteHands();
        //setInterval("renderTime()", 1000);
    }
    function getTimes()
    {
    	moment.tz.add([
    	'Europe/London|GMT BST|0 -10|01010101010101010101010|1GNB0 1qM0 11A0 1o00 11A0 1o00 11A0 1o00 11A0 1qM0 WM0 1qM0 WM0 1qM0 11A0 1o00 11A0 1o00 11A0 1qM0 WM0 1qM0|10e6',
    	'Asia/Tokyo|JST|-90|0||38e6',
    	'America/Phoenix|MST|70|0||42e5'
    	]);
  		var now = new Date();
  		// Set the time manually for each of the clock types we're using
  		var times = [
    	{
      		jsclass: 'js-phoenix',
      		jstime: moment.tz("America/Phoenix")
    	},
    	{
      		jsclass: 'js-london',
      		jstime: moment.tz("Europe/London")
    	},
    	{
      		jsclass: 'js-tokyo',
      		jstime: moment.tz("Asia/Tokyo")
    	}
  		];
  		return times;
    }
    
    function renderInternational()
    {
    	var times = getTimes();
  		for (i = 0; i < times.length; ++i) {
    		var h = times[i].jstime.format('h');
    		var m = times[i].jstime.format('mm');
    		var s = times[i].jstime.format('ss');

    		var degrees = [
      		{
        		hand: 'hours',
        		degree: (h * 30) + (m / 2)
      		},
      		{
      		  	hand: 'minutes',
        		degree: (m * 6)
      		},
      		{
        		hand: 'seconds',
        		degree: (s * 6)
      		}
    		];
    		for (var j = 0; j < degrees.length; j++) {
      			var elements = document.querySelectorAll('.active .' + times[i].jsclass + ' .' + degrees[j].hand);
      			for (var k = 0; k < elements.length; k++) {
        			elements[k].style.webkitTransform = 'rotate('+ degrees[j].degree +'deg)';
          			elements[k].style.transform = 'rotate('+ degrees[j].degree +'deg)';
          			// If this is a minute hand, note the seconds position (to calculate minute position later)
          			if (degrees[j].hand === 'minutes') {
            			elements[k].parentNode.setAttribute('data-second-angle', degrees[j + 1].degree);
          			}
      			}
    		}
  		}
    }
    function renderTime()
    {
    	var times = getTimes();
    	Phoenix = {
    		hour: times[0].jstime.format('h'),
    		min: times[0].jstime.format('mm'),
    		amPM: times[0].jstime.format('A'),
    		h12: times[0].jstime.format('HH')
    	}
    	document.getElementById("timePhoenix").innerHTML = Phoenix.hour + ":" + Phoenix.min + " " + Phoenix.amPM;
    	
    	London = {
    		hour: times[1].jstime.format('h'),
    		min: times[1].jstime.format('mm'),
    		amPM: times[1].jstime.format('A'),
    		h12: times[1].jstime.format('HH')
    	}
    	document.getElementById("timeLondon").innerHTML = London.hour + ":" + London.min + " " + London.amPM;
    	
    	Tokyo = {
    		hour: times[2].jstime.format('h'),
    		min: times[2].jstime.format('mm'),
    		amPM: times[2].jstime.format('A'),
    		h12: times[2].jstime.format('HH')
    	}
    	document.getElementById("timeTokyo").innerHTML = Tokyo.hour + ":" + Tokyo.min + " " + Tokyo.amPM;
    	
    	if(Phoenix.h12 + 8 >= 24)
    	{
    		document.getElementById("dayLondon").innerHTML = "Tomorrow, +8HRS";
    	}
    	else
    	{
    		document.getElementById("dayLondon").innerHTML = "Today, +8HRS";
    	}
    	
    	if(Phoenix.h12 + 16 >= 24)
    	{
    		document.getElementById("dayTokyo").innerHTML = "Tomorrow, +16HRS";
    	}
    	else 
    		document.getElementById("dayTokyo").innerHTML = "Today, +16HRS";
    }
    function showClock(city)
    {
    	// Add a class to the clock container to show it
  		var elements = document.getElementById(city);
  		elements.className = elements.className + ' show';
  		/*for (var l = 0; l < elements.length; l++) {
    		elements[l].className = elements[l].className + ' show';
  		}*/
  		
  		if(city === "london")
  		{
  			document.getElementById("tokyo").style.display = "none";
  			document.getElementById("phoenix").style.display = "none";
  			if(London.amPM === "AM")
  			{
  				document.getElementById("body").className = document.getElementById("body").className + " day";
  				document.getElementById("bodyTitle").className = document.getElementById("bodyTitle").className + " day";
  				document.getElementById("btnBack").className = document.getElementById("btnBack").className + " day";
  			}
  		}
  		else if(city === "tokyo")
  		{
  			document.getElementById("london").style.display = "none";
  			document.getElementById("phoenix").style.display = "none";
  			if(Tokyo.amPM === "AM")
  			{
  				document.getElementById("body").className = document.getElementById("body").className + " day";
  				document.getElementById("bodyTitle").className = document.getElementById("bodyTitle").className + " day";
  				document.getElementById("btnBack").className = document.getElementById("btnBack").className + " day";
  			}
  		}
  		else if(city === "phoenix")
  		{
  			document.getElementById("london").style.display = "none";
  			document.getElementById("tokyo").style.display = "none";
  			if(Phoenix.amPM === "AM")
  			{
  				document.getElementById("body").className = document.getElementById("body").className + " day";
  				document.getElementById("bodyTitle").className = document.getElementById("bodyTitle").className + " day";
  				document.getElementById("btnBack").className = document.getElementById("btnBack").className + " day";
  			}
  		}
  		
  		document.getElementById("btnBack").style.display = "initial";
  		document.getElementById("timeZone1").className = document.getElementById("timeZone1").className + " hide";
  		document.getElementById("timeZone2").className = document.getElementById("timeZone2").className + " hide";
        document.getElementById("timeZone3").className = document.getElementById("timeZone3").className + " hide";
    }
	function moveSecondHands() {
  		var containers = document.querySelectorAll('.bounce .seconds-container');
  		setInterval(function() {
    		for (var i = 0; i < containers.length; i++) {
      			if (containers[i].angle === undefined) {
        			containers[i].angle = 6;
      			} else {
        			containers[i].angle += 6;
      			}
      				containers[i].style.webkitTransform = 'rotateZ('+ containers[i].angle +'deg)';
      			containers[i].style.transform = 'rotateZ('+ containers[i].angle +'deg)';
    		}
  		}, 1000);
  		for (var i = 0; i < containers.length; i++) {
    		// Add in a little delay to make them feel more natural
    		var randomOffset = Math.floor(Math.random() * (100 - 10 + 1)) + 10;
    		containers[i].style.transitionDelay = '0.0'+ randomOffset +'s';
  		}
	}
	function setUpMinuteHands() {
  		// More tricky, this needs to move the minute hand when the second hand hits zero
  		var containers = document.querySelectorAll('.minutes-container');
  		var secondAngle = containers[containers.length - 1].getAttribute('data-second-angle');
  		console.log(secondAngle);
  		if (secondAngle > 0) {
    		// Set a timeout until the end of the current minute, to move the hand
    		var delay = (((360 - secondAngle) / 6) + 0.1) * 1000;
    		console.log(delay);
    		setTimeout(function() {
      			moveMinuteHands(containers);
    		}, delay);
  		}
	}
	function moveMinuteHands(containers) {
  		for (var i = 0; i < containers.length; i++) {
    		containers[i].style.webkitTransform = 'rotateZ(6deg)';
    		containers[i].style.transform = 'rotateZ(6deg)';
  		}
  		// Then continue with a 60 second interval
  		setInterval(function() {
    		for (var i = 0; i < containers.length; i++) {
      			if (containers[i].angle === undefined) {
        			containers[i].angle = 12;
      			} else {
        			containers[i].angle += 6;
      			}
      			containers[i].style.webkitTransform = 'rotate('+ containers[i].angle +'deg)';
      			containers[i].style.transform = 'rotate('+ containers[i].angle +'deg)';
    		}
  		}, 60000);
	}
	function back()
	{
		document.getElementById("btnBack").className = "back";
		document.getElementById("btnBack").style.display = "none";
		document.getElementById("bodyTitle").className = "title";
  		document.getElementById("timeZone1").className = "row";
  		document.getElementById("timeZone2").className = "row";
        document.getElementById("timeZone3").className = "row";
        document.getElementById("body").className = "bodyClass";
        document.getElementById("phoenix").className = "col-md-6 clock station js-phoenix";
        document.getElementById("london").className = "col-md-6 clock station js-london";
        document.getElementById("tokyo").className = "col-md-6 clock station js-tokyo";
	}