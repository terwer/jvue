/**
 * axios
 *
 *@author Terwer
 *@version 1.0
 *2019/3/19 16:39
 **/
export default function({ $axios, redirect }) {
  $axios.onRequest(config => {
    console.log("Making request to " + config.baseURL + config.url);
  });

  $axios.onError(error => {
    const code = parseInt(error.response && error.response.status);
    if (code === 400) {
      redirect("/400");
    } else if (code === 500) {
      redirect("/sorry");
    }
  });
}
