package com.saloonme.util;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;
import android.util.Pair;
import android.widget.Toast;

import androidx.core.content.FileProvider;

import com.saloonme.interfaces.APIConstants;
import com.saloonme.interfaces.StringConstants;
import com.saloonme.network.APIClient;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.concurrent.ExecutionException;


import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShareUtil {
    String fileName;
    Context context;
    DownloadAudioFileTask downloadAudioFileTask;
    DownloadImageFileTask downloadImageFileTask;
    DownloadGifFileTask downloadGifFileTask;
    private static final String TAG = "ShareUtil";
    onAudioFileShareListener onAudioFileShareListener;
    private boolean onMoreClicked;

    public ShareUtil(Context context, onAudioFileShareListener onAudioFileShareListener) {
        this.context = context;
        this.onAudioFileShareListener = onAudioFileShareListener;
    }

    public void shareAudioFile(String file, boolean onMoreClicked) {
        this.onMoreClicked = onMoreClicked;
        if (file != null && !file.isEmpty() && !file.contains("http://localhost")) {
            file = file.replace(APIConstants.BASE_URL, "");
            if (file != null && !file.isEmpty() && file.contains("/")) {
                String splitedFile[] = file.split("/");
                fileName = splitedFile[splitedFile.length - 1];
                Call<ResponseBody> call = APIClient.getAPIService().downloadFileByUrl(file);
                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()) {
                            downloadAudioFileTask = new DownloadAudioFileTask();
                            try {
                                boolean sharedSuccessfully = downloadAudioFileTask.execute(response.body()).get();
                                if (onAudioFileShareListener != null)
                                    onAudioFileShareListener.onShareListener(sharedSuccessfully);
                            } catch (ExecutionException e) {
                                e.printStackTrace();
                                if (onAudioFileShareListener != null)
                                    onAudioFileShareListener.onShareListener(false);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                                if (onAudioFileShareListener != null)
                                    onAudioFileShareListener.onShareListener(false);
                            }
                        } else {
                            Toast.makeText(context, "File not found to share", Toast.LENGTH_SHORT).show();
                            if (onAudioFileShareListener != null)
                                onAudioFileShareListener.onShareListener(false);
                        }

                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Toast.makeText(context, "File not found to share", Toast.LENGTH_SHORT).show();
                        if (onAudioFileShareListener != null)
                            onAudioFileShareListener.onShareListener(false);
                    }
                });
            }
        } else {
            Toast.makeText(context, "File not found to share", Toast.LENGTH_SHORT).show();
            if (onAudioFileShareListener != null)
                onAudioFileShareListener.onShareListener(false);
        }
    }

    public void shareImageFile(String file, boolean onMoreClicked) {
        this.onMoreClicked = onMoreClicked;
        if (file != null && !file.isEmpty() && !file.contains("http://localhost")) {
           // file = file.replace(APIConstants.BASE_URL, "");
            if (file != null && !file.isEmpty() && file.contains("/")) {
                String splitedFile[] = file.split("/");
                fileName = splitedFile[splitedFile.length - 1];
                Call<ResponseBody> call = APIClient.getAPIService().downloadFileByUrl(file);
                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()) {
                            downloadImageFileTask = new DownloadImageFileTask();
                            try {
                                boolean sharedSuccessfully = downloadImageFileTask.execute(response.body()).get();
                                if (onAudioFileShareListener != null)
                                    onAudioFileShareListener.onShareListener(sharedSuccessfully);
                            } catch (ExecutionException e) {
                                e.printStackTrace();
                                if (onAudioFileShareListener != null)
                                    onAudioFileShareListener.onShareListener(false);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                                if (onAudioFileShareListener != null)
                                    onAudioFileShareListener.onShareListener(false);
                            }
                        } else {
                            Toast.makeText(context, "File not found to share", Toast.LENGTH_SHORT).show();
                            if (onAudioFileShareListener != null)
                                onAudioFileShareListener.onShareListener(false);
                        }

                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Toast.makeText(context, "File not found to share", Toast.LENGTH_SHORT).show();
                        if (onAudioFileShareListener != null)
                            onAudioFileShareListener.onShareListener(false);
                    }
                });
            }
        } else {
            Toast.makeText(context, "File not found to share", Toast.LENGTH_SHORT).show();
            if (onAudioFileShareListener != null)
                onAudioFileShareListener.onShareListener(false);
        }
    }

    public void shareGifFile(String file, boolean onMoreClicked) {
        this.onMoreClicked = onMoreClicked;
        if (file != null && !file.isEmpty() && !file.contains("http://localhost")) {
            file = file.replace(APIConstants.BASE_URL, "");
            if (file != null && !file.isEmpty() && file.contains("/")) {
                String splitedFile[] = file.split("/");
                fileName = splitedFile[splitedFile.length - 1];
                Call<ResponseBody> call = APIClient.getAPIService().downloadFileByUrl(file);
                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()) {
                            downloadGifFileTask = new DownloadGifFileTask();
                            try {
                                boolean sharedSuccessfully = downloadGifFileTask.execute(response.body()).get();
                                if (onAudioFileShareListener != null)
                                    onAudioFileShareListener.onShareListener(sharedSuccessfully);
                            } catch (ExecutionException e) {
                                e.printStackTrace();
                                if (onAudioFileShareListener != null)
                                    onAudioFileShareListener.onShareListener(false);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                                if (onAudioFileShareListener != null)
                                    onAudioFileShareListener.onShareListener(false);
                            }
                        } else {
                            Toast.makeText(context, "File not found to share", Toast.LENGTH_SHORT).show();
                            if (onAudioFileShareListener != null)
                                onAudioFileShareListener.onShareListener(false);
                        }

                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Toast.makeText(context, "File not found to share", Toast.LENGTH_SHORT).show();
                        if (onAudioFileShareListener != null)
                            onAudioFileShareListener.onShareListener(false);
                    }
                });
            }
        } else {
            Toast.makeText(context, "File not found to share", Toast.LENGTH_SHORT).show();
            if (onAudioFileShareListener != null)
                onAudioFileShareListener.onShareListener(false);
        }
    }

    public class DownloadAudioFileTask extends AsyncTask<ResponseBody, Pair<Integer, Long>, Boolean> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected Boolean doInBackground(ResponseBody... responseBodies) {
            boolean sharedSuccessfully = saveToDisk(responseBodies[0], fileName, StringConstants.AUDIO);
            return sharedSuccessfully;
        }

        protected void onProgressUpdate(Pair<Integer, Long>... progress) {

            // Log.e("API123", progress[0].second + " ");

            if (progress[0].first == 100) {
                // progressBar.setVisibility(View.GONE);
                //Toast.makeText(getApplicationContext(), "File downloaded successfully", Toast.LENGTH_SHORT).show();

            }


            if (progress[0].second > 0) {
                int currentProgress = (int) ((double) progress[0].first / (double) progress[0].second * 100);
                //  progressBar.setProgress(currentProgress);
                Log.e("Progress", currentProgress + "%");
                // txtProgressPercent.setText("Progress " + currentProgress + "%");

            }

            if (progress[0].first == -1) {
                // progressBar.setVisibility(View.GONE);
                Toast.makeText(context, "Unable to share the file,try again later", Toast.LENGTH_SHORT).show();
            }

        }

        public void doProgress(Pair<Integer, Long> progressDetails) {
            publishProgress(progressDetails);
        }

        @Override
        protected void onPostExecute(Boolean s) {
            super.onPostExecute(s);
        }
    }

    public class DownloadImageFileTask extends AsyncTask<ResponseBody, Pair<Integer, Long>, Boolean> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected Boolean doInBackground(ResponseBody... responseBodies) {
            boolean sharedSuccessfully = saveToDisk(responseBodies[0], fileName, StringConstants.IMAGE);
            return sharedSuccessfully;
        }

        protected void onProgressUpdate(Pair<Integer, Long>... progress) {

            // Log.e("API123", progress[0].second + " ");

            if (progress[0].first == 100) {
                // progressBar.setVisibility(View.GONE);
                //Toast.makeText(getApplicationContext(), "File downloaded successfully", Toast.LENGTH_SHORT).show();

            }


            if (progress[0].second > 0) {
                int currentProgress = (int) ((double) progress[0].first / (double) progress[0].second * 100);
                //  progressBar.setProgress(currentProgress);
                Log.e("Progress", currentProgress + "%");
                // txtProgressPercent.setText("Progress " + currentProgress + "%");

            }

            if (progress[0].first == -1) {
                // progressBar.setVisibility(View.GONE);
                Toast.makeText(context, "Unable to share the file,try again later", Toast.LENGTH_SHORT).show();
            }

        }

        public void doProgress(Pair<Integer, Long> progressDetails) {
            publishProgress(progressDetails);
        }

        @Override
        protected void onPostExecute(Boolean s) {
            super.onPostExecute(s);
        }
    }

    public class DownloadGifFileTask extends AsyncTask<ResponseBody, Pair<Integer, Long>, Boolean> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected Boolean doInBackground(ResponseBody... responseBodies) {
            boolean sharedSuccessfully = saveToDisk(responseBodies[0], fileName, StringConstants.GIF);
            return sharedSuccessfully;
        }

        protected void onProgressUpdate(Pair<Integer, Long>... progress) {

            // Log.e("API123", progress[0].second + " ");

            if (progress[0].first == 100) {
                // progressBar.setVisibility(View.GONE);
                //Toast.makeText(getApplicationContext(), "File downloaded successfully", Toast.LENGTH_SHORT).show();

            }


            if (progress[0].second > 0) {
                int currentProgress = (int) ((double) progress[0].first / (double) progress[0].second * 100);
                //  progressBar.setProgress(currentProgress);
                Log.e("Progress", currentProgress + "%");
                // txtProgressPercent.setText("Progress " + currentProgress + "%");

            }

            if (progress[0].first == -1) {
                // progressBar.setVisibility(View.GONE);
                Toast.makeText(context, "Unable to share the file,try again later", Toast.LENGTH_SHORT).show();
            }

        }

        public void doProgress(Pair<Integer, Long> progressDetails) {
            publishProgress(progressDetails);
        }

        @Override
        protected void onPostExecute(Boolean s) {
            super.onPostExecute(s);
        }
    }

    public boolean saveToDisk(ResponseBody body, String filename, String fileType) {
        boolean sharedSuccessfully = false;
        try {

            File destinationFile = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), filename);

            InputStream inputStream = null;
            OutputStream outputStream = null;
            try {
                inputStream = body.byteStream();
                outputStream = new FileOutputStream(destinationFile);
                byte data[] = new byte[4096];
                int count;
                int progress = 0;
                long fileSize = body.contentLength();
                Log.e(TAG, "File Size=" + fileSize);
                while ((count = inputStream.read(data)) != -1) {
                    outputStream.write(data, 0, count);
                    progress += count;
                    Pair<Integer, Long> pairs = new Pair<>(progress, fileSize);
                    if (fileType.equalsIgnoreCase(StringConstants.AUDIO)) {
                        downloadAudioFileTask.doProgress(pairs);
                    } else if (fileType.equalsIgnoreCase(StringConstants.GIF)) {
                        downloadGifFileTask.doProgress(pairs);
                    } else if (fileType.equalsIgnoreCase(StringConstants.IMAGE)) {
                        downloadImageFileTask.doProgress(pairs);
                    }

                    Log.e(TAG, "Progress: " + progress + "/" + fileSize + " >>>> " + (float) progress / fileSize);
                }

                outputStream.flush();
                Log.e("parent", destinationFile.getParent());
                Pair<Integer, Long> pairs = new Pair<>(100, 100L);
                //to get local path
                String sharePath = destinationFile.getAbsolutePath();
                if (fileType.equalsIgnoreCase(StringConstants.AUDIO)) {
                    downloadAudioFileTask.doProgress(pairs);
                    File sharingGifFile = new File(sharePath);
                    Intent shareIntent = new Intent(Intent.ACTION_SEND);
                    shareIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                    Uri uri = FileProvider.getUriForFile(
                            context,
                            this.context
                                    .getPackageName() + ".provider", sharingGifFile);
                    if (!onMoreClicked)
                        shareIntent.setPackage("com.whatsapp");
                    shareIntent.setDataAndType(uri, StringConstants.AUDIO);
                    shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                    List<ResolveInfo> resInfoList = context.getPackageManager().queryIntentActivities(shareIntent, PackageManager.MATCH_DEFAULT_ONLY);
                    for (ResolveInfo resolveInfo : resInfoList) {
                        String packageName = resolveInfo.activityInfo.packageName;
                        context.grantUriPermission(packageName, uri, Intent.FLAG_GRANT_WRITE_URI_PERMISSION | Intent.FLAG_GRANT_READ_URI_PERMISSION);
                    }
                    shareIntent.setType("audio/*");
                    shareIntent.putExtra(Intent.EXTRA_STREAM, uri);
                    context.startActivity(Intent.createChooser(shareIntent, "Share file"));
                    sharedSuccessfully = true;
                } else if (fileType.equalsIgnoreCase(StringConstants.GIF)) {
                     downloadGifFileTask.doProgress(pairs);
                    File sharingGifFile = new File(sharePath);
                    Intent shareIntent = new Intent(Intent.ACTION_SEND);
                    shareIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                    Uri uri = FileProvider.getUriForFile(
                            context,
                            this.context
                                    .getPackageName() + ".provider", sharingGifFile);
                    if (!onMoreClicked)
                        shareIntent.setPackage("com.whatsapp");
                    shareIntent.setDataAndType(uri, StringConstants.GIF);
                    shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                    List<ResolveInfo> resInfoList = context.getPackageManager().queryIntentActivities(shareIntent, PackageManager.MATCH_DEFAULT_ONLY);
                    for (ResolveInfo resolveInfo : resInfoList) {
                        String packageName = resolveInfo.activityInfo.packageName;
                        context.grantUriPermission(packageName, uri, Intent.FLAG_GRANT_WRITE_URI_PERMISSION | Intent.FLAG_GRANT_READ_URI_PERMISSION);
                    }
                    shareIntent.setType("image/gif");
                    shareIntent.putExtra(Intent.EXTRA_STREAM, uri);
                    context.startActivity(Intent.createChooser(shareIntent, "Share Gif"));
                    sharedSuccessfully = true;
                } else if (fileType.equalsIgnoreCase(StringConstants.IMAGE)) {
                    downloadImageFileTask.doProgress(pairs);
                    File sharingImageFile = new File(sharePath);
                    Intent shareIntent = new Intent(Intent.ACTION_SEND);
                    shareIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                    Uri uri = FileProvider.getUriForFile(
                            context,
                            this.context
                                    .getPackageName() + ".provider", sharingImageFile);
                    if (!onMoreClicked)
                        shareIntent.setPackage("com.whatsapp");
                    shareIntent.setDataAndType(uri, StringConstants.IMAGE);
                    shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                    List<ResolveInfo> resInfoList = context.getPackageManager().queryIntentActivities(shareIntent, PackageManager.MATCH_DEFAULT_ONLY);
                    for (ResolveInfo resolveInfo : resInfoList) {
                        String packageName = resolveInfo.activityInfo.packageName;
                        context.grantUriPermission(packageName, uri, Intent.FLAG_GRANT_WRITE_URI_PERMISSION | Intent.FLAG_GRANT_READ_URI_PERMISSION);
                    }
                    shareIntent.setType("image/*");
                    shareIntent.putExtra(Intent.EXTRA_STREAM, uri);
                    context.startActivity(Intent.createChooser(shareIntent, "Share Image"));
                    sharedSuccessfully = true;
                }
            } catch (IOException e) {
                e.printStackTrace();
                Pair<Integer, Long> pairs = new Pair<>(-1, Long.valueOf(-1));
                if (fileType.equalsIgnoreCase(StringConstants.AUDIO)) {
                    downloadAudioFileTask.doProgress(pairs);
                } else if (fileType.equalsIgnoreCase(StringConstants.GIF)) {
                    //downloadGifFileTask.doProgress(pairs);
                } else if (fileType.equalsIgnoreCase(StringConstants.IMAGE)) {
                    downloadImageFileTask.doProgress(pairs);
                }
                Toast.makeText(context, "Unable to share the file,try again later", Toast.LENGTH_SHORT).show();
                return sharedSuccessfully;
            } finally {
                if (inputStream != null) inputStream.close();
                if (outputStream != null) outputStream.close();
                return sharedSuccessfully;
            }
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(context, "File not found to share", Toast.LENGTH_SHORT).show();
            return sharedSuccessfully;

        }

    }

    public interface onAudioFileShareListener {
        void onShareListener(boolean sharedSuccessfully);
    }
}
