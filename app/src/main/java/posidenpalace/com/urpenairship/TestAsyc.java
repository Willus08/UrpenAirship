package posidenpalace.com.urpenairship;

import android.os.AsyncTask;



public class TestAsyc extends AsyncTask<String,Integer, String> {



    @Override
    protected String doInBackground(String... params) {

        for (int i = 0; i <10 ; i++) {
            try{
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("doInBackground" + Thread.currentThread());
            publishProgress(i);

        }


        return "Done with Task";
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        //textView1.setText("Startig Async");
        //progress.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        //textView1.setText(String.valueOf(values[0]));
        //progress.setProgress(values[0]*10);

    }


    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        Startup.instance.MustDie(this);
        //textView1.setText(s);
        //progress.setVisibility(View.GONE);
        //progress.setProgress(0);
    }

}