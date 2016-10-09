package com.example.jay.udacitypopularmovies;

import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;

import com.raizlabs.android.dbflow.annotation.ConflictAction;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.structure.ModelAdapter;

import static com.example.jay.udacitypopularmovies.Movie.ENDPOINT;

/**
 * Created by Jay on 2016-10-09.
 */
@com.raizlabs.android.dbflow.annotation.provider.ContentProvider(
        authority = ContentProvider.AUTHORITY,
        database = PopularMoviesDatabase.class,
        baseContentUri = ContentProvider.BASE_CONTENT_URI
)
public class ContentProvider extends android.content.ContentProvider {

    public static final String AUTHORITY = "com.example.jay.udacitypopularmovies.provider";
    public static final String BASE_CONTENT_URI = "content://";

    public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY
            + "/" + ENDPOINT);



    private static final int feeds_CONTENT_URI = 0;

    private static final UriMatcher MATCHER = new UriMatcher(UriMatcher.NO_MATCH);
    static {
        MATCHER.addURI(AUTHORITY, ENDPOINT, feeds_CONTENT_URI);
    }
    ;



    @Override
    public final String getType(Uri uri) {
        String type = null;
        switch(MATCHER.match(uri)) {
            case feeds_CONTENT_URI: {
                type = "vnd.android.cursor.dir/" +ENDPOINT;
                break;
            }
            default: {
                throw new IllegalArgumentException("Unknown URI" + uri);
            }
        }
        return type;
    }

    @Override
    public boolean onCreate() {
        return false;
    }


    @Override
    public final Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        android.database.Cursor cursor = null;
        switch(MATCHER.match(uri)) {
            case feeds_CONTENT_URI: {
                cursor = FlowManager.getDatabase(PopularMoviesDatabase.class).getWritableDatabase().query("Movie.class", projection, selection, selectionArgs, null, null, sortOrder);
                break;
            }
        }
        if (cursor != null) {
            cursor.setNotificationUri(getContext().getContentResolver(), uri);
        }
        return cursor;
    }

    @Override
    public final Uri insert(Uri uri, ContentValues values) {
        switch(MATCHER.match(uri)) {
            case feeds_CONTENT_URI: {
                ModelAdapter adapter = FlowManager.getModelAdapter(FlowManager.getTableClassForName("PopularMoviesDatabase.class", "Movie.class"));
                final long id = FlowManager.getDatabase(PopularMoviesDatabase.class).getWritableDatabase().insertWithOnConflict("Movie.class", null, values, ConflictAction.getSQLiteDatabaseAlgorithmInt(adapter.getInsertOnConflictAction()));
                getContext().getContentResolver().notifyChange(uri, null);
                return ContentUris.withAppendedId(uri, id);
            }
            default: {
                throw new IllegalStateException("Unknown Uri" + uri);
            }
        }
    }


    @Override
    public final int delete(Uri uri, String selection, String[] selectionArgs) {
        switch(MATCHER.match(uri)) {
            case feeds_CONTENT_URI: {
                long count = FlowManager.getDatabase(PopularMoviesDatabase.class).getWritableDatabase().delete("Movie.class", selection, selectionArgs);
                if (count > 0) {
                    getContext().getContentResolver().notifyChange(uri, null);
                }
                return (int) count;
            }
            default: {
                throw new IllegalArgumentException("Unknown URI" + uri);
            }
        }
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        switch (MATCHER.match(uri)) {
            case feeds_CONTENT_URI: {
                ModelAdapter adapter = FlowManager.getModelAdapter(FlowManager.getTableClassForName("PopularMoviesDatabase.class", "Movie.class"));
                long count = FlowManager.getDatabase(PopularMoviesDatabase.class).getWritableDatabase().updateWithOnConflict("Movie.class", values, selection, selectionArgs, ConflictAction.getSQLiteDatabaseAlgorithmInt(adapter.getUpdateOnConflictAction()));
                if (count > 0) {
                    getContext().getContentResolver().notifyChange(uri, null);
                }
                return (int) count;
            }
            default: {
                throw new IllegalStateException("Unknown Uri" + uri);
            }
        }

    }
}
