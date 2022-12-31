<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>AIoogle</title>
	<style type="text/css">
		h1, #description, #go{
			font-family: Papyrus, sans-serif, tahoma; 
		}
		h1 {
			font-size: 55pt;
			color: #FADBD8;
			position: relative;
			top: 80px;
		}
		#description{
			color: #EAF2F8;
			font-size: 12pt;
			font-weight: bold;
			position: relative;
			top: 80px;
		}
		#search{
			position: relative;
			top: 100px;
		}
        #options{
			font-family: Arial black, Times New Roman, Times;
			font-size: 14px;
			color: #EAF2F8;
            position: absolute;
			top: 285px;
			left: 510px;
        }
		*{
			margin: 0;
		}
		.bg{
			background-image: url("background.jpg");
			height: 100vh;
			background-size: cover;
			background-repeat: no-repeat;
		}

	</style>
</head>
<body>
	<div class="bg">
		<h1 id="title" align="center">Aioog!e</h1>
		<p id="description" align="center">
			<i>The BEST search engine for Artificial Intelligence resource...</i>
		</p>
		<form method="post" action="Handler">
			<div id="search" align="center"> 
				<input id="input" type="text" style="width:220px; height:25px" placeholder='search "AI"' value="" name="query">
				<input id="go" type="submit" value="go" style="height:31px">
			</div>
			<div id="options">
				<input type="radio" name="platform" value="hahow" checked>Hahow<br>
				<input type="radio" name="platform" value="coursera">Coursera<br>
				<input type="radio" name="platform" value="udemy">Udemy<br>
				<input type="radio" name="platform" value=" hiskio">HiSKIO<br>
				<input type="radio" name="platform" value="tibame">TibaMe<br>
			</div>
		</form>
	</div>

</body>
</html>