@file:OptIn(DelicateCoroutinesApi::class)

package com.nuhin13.myapplication

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nuhin13.myapplication.ui.retrofit.GraphQLInstance
import com.nuhin13.myapplication.ui.theme.MyApplicationTheme
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.json.JSONObject


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    //Greeting("Android")
                    //DetailsContent()
                    //Toast.makeText(this, "bugi", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}

// Function to generate a Toast
private fun mToast(context: Context){
    Toast.makeText(context, "This is a Sample Toast", Toast.LENGTH_LONG).show()
}

@Composable
fun SomeContent(){
    // Fetching the local context for using the Toast
    val mContext = LocalContext.current
    // Creating a Box layout to display a Button
    Box(Modifier.fillMaxSize(), Alignment.Center) {
        // Creating a Button and calling the
        // Toast function when clicked
        Button(onClick = { post() },
            colors = ButtonDefaults.buttonColors()) {
            Text(text = "Click", color = Color.White)
        }
    }
}


private fun post(){
    val retrofit = GraphQLInstance.graphQLService
    val paramObject = JSONObject()
    paramObject.put("query", "query getCategories(pagination: { limit: 10, skip: 0 }) {\n" +
            "\t\tresult {\n" +
            "\t\t\tcategories {\n" +
            "\t\t\t\tuid\n" +
            "\t\t\t\tenName\n" +
            "\t\t\t\tparent {\n" +
            "\t\t\t\t\tuid\n" +
            "\t\t\t\t\tenName\n" +
            "\t\t\t\t}\n" +
            "\t\t\t\tparents {\n" +
            "\t\t\t\t\tuid\n" +
            "\t\t\t\t\tenName\n" +
            "\t\t\t\t}\n" +
            "\t\t\t\timage {\n" +
            "\t\t\t\t\tname\n" +
            "\t\t\t\t\turl\n" +
            "\t\t\t\t\tsignedUrl\n" +
            "\t\t\t\t}\n" +
            "\t\t\t\tattributeSetUid\n" +
            "\t\t\t\tisActive\n" +
            "\t\t\t\tinActiveNote\n" +
            "\t\t\t\tcreatedAt\n" +
            "\t\t\t\tupdatedAt\n" +
            "\t\t\t}\n" +
            "\t\t}\n" +
            "\t}")
    GlobalScope.launch {
        try {
            val response = retrofit.postDynamicQuery(paramObject.toString())
            Log.e("response ", response.body().toString())
        }catch (e: java.lang.Exception){
            Log.e("error ", e.toString())
            e.printStackTrace()
        }
    }
}

object Details {

    val EmployDetailsList = listOf(
        EmployDetails(
            id = 1,
            title = "Rohan",
            sex = "Male",
            age = 24,
            description = " Don't judge each day by the harvest you reap but by the seeds that you plant.” - ...",
            ImageId = R.drawable.ic_launcher_background
        ),
        EmployDetails(
            id = 2,
            title = "Roy",
            sex = "male",
            age = 25,
            description = " Don't judge each day by the harvest you reap but by the seeds that you plant.” - ...",
            ImageId = R.drawable.ic_launcher_background
        ),
        EmployDetails(
            id = 3,
            title = "Vishal",
            sex = "Male",
            age = 29,
            description = " Don't judge each day by the harvest you reap but by the seeds that you plant.” - ...",
            ImageId = R.drawable.ic_launcher_background
        ),
        EmployDetails(
            id = 4,
            title = "Nikhil",
            sex = "Male",
            age = 27,
            description = " Don't judge each day by the harvest you reap but by the seeds that you plant.” - ...",
            ImageId = R.drawable.ic_launcher_background
        ))
}


@Composable
fun DetailsContent() {
    val employees = remember { Details.EmployDetailsList }
    LazyColumn(
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
    ) {
        items(
            employees
        ) {
            EmployeeCard(emp = it)
        }
    }
}

@Composable
fun Greeting(name: String) {
    Row {
        Text(text = "Hello $name!")
        Text(text = "Hello $name!")
        Text(text = "Hello $name!")
        Text(text = "Hello $name!")
        Text(text = "Hello $name!")
    }
}

@Composable
fun EmployeeCard(emp: EmployDetails) {
    Card(
        modifier = Modifier
            .padding(horizontal = 8.dp, vertical = 8.dp)
            .fillMaxWidth(),
        elevation = 2.dp,
        backgroundColor = Color.LightGray,
        shape = RoundedCornerShape(corner = CornerSize(16.dp))

    ) {

        Row(modifier = Modifier.padding(20.dp)) {
            Column(modifier = Modifier.weight(1f),
                Arrangement.Center) {
                Text(
                    text = emp.title,
                    style = TextStyle(
                        color = Color.Black,
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                    )
                )
                Text(
                    text = "Age :- "+emp.age.toString(),
                    style = TextStyle(
                        color = Color.Black,
                        fontSize = 15.sp
                    )
                )
                Text(
                    text = "Sex :- "+emp.sex,
                    style = TextStyle(
                        color = Color.Black,
                        fontSize = 15.sp
                    )
                )

                Text(
                    text = "Description :- "+emp.description,
                    style = TextStyle(
                        color = Color.Black,
                        fontSize = 15.sp
                    )
                )
            }
            Image(painter = painterResource(emp.ImageId), contentDescription = "Profile Image",
                contentScale = ContentScale.FillHeight,
                modifier = Modifier
                    .padding(8.dp)
                    .size(110.dp)
                    .clip((CircleShape)))
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyApplicationTheme {
        SomeContent()
        //DetailsContent()
    }
}

data class EmployDetails(val id: Int,
                         val title: String,
                         val sex: String,
                         val age: Int,
                         val description: String,
                         val ImageId: Int = 0)

