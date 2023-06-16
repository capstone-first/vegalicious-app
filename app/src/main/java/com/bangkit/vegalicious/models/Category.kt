package com.bangkit.vegalicious.models

data class Category(
	val id: String,
	val title: String,
	val photoUrl: String = "https://assets.epicurious.com/photos/63b5b03305dd27a0d03a18a6/1:1/w_1920,c_limit/Jackfruit%20curry-RECIPE.jpg",
)

val topCategories = listOf(
	Category(
		"9",
		"Quick & Easy",
		"https://cook.fnr.sndimg.com/content/dam/images/cook/fullset/2012/8/20/0/CCBKN214_Sloppy-Gino_s4x3.jpg.rend.hgtvcom.616.462.suffix/1386025040837.jpeg"
	),
	Category(
		"1",
		"Pescatarian",
		"https://res.cloudinary.com/dk0z4ums3/image/upload/v1607301135/attached_image/mengenal-pola-makan-pescatarian-dan-manfaatnya-bagi-kesehatan.jpg"
	),
	Category(
		"2",
		"Peanut Free",
		"https://peanut-institute.com/wp-content/uploads/2023/02/about-peanuts.jpg"
		),
	Category(
		"3",
		"Dairy Free",
		"https://media.istockphoto.com/id/1312934705/vector/lactose-free-sign-icon-logo-round-badge-with-milk-bottle-crossed-out.jpg?s=612x612&w=0&k=20&c=tT8voQJE7KRNPfzS02PH1v_zKiISa_S-5_Rnhana9eA="
	),
	Category(
		"4",
		"Soy Free",
		"https://cdn4.vectorstock.com/i/1000x1000/10/18/soy-free-logo-vector-6901018.jpg"
	),
	Category(
		"5",
		"Wheat/Gluten-Free",
		"https://rey.id/blog/wp-content/uploads/2023/02/gluten-free.jpg"
	),
	Category(
		"6",
		"Sides",
		"https://i2.wp.com/www.downshiftology.com/wp-content/uploads/2020/11/Healthy-Thanksgiving-Side-Dishes-1.jpg"
	),
	Category(
		"7",
		"Healthy",
		"https://i0.wp.com/images-prod.healthline.com/hlcmsresource/images/AN_images/healthy-eating-ingredients-1296x728-header.jpg?w=1155&h=1528"
	),
	Category(
		"8",
		"Salad",
		"https://cdn.loveandlemons.com/wp-content/uploads/2021/04/green-salad-500x375.jpg"
	),
)