package UI;

import Enums.MapTile.MapType;
import InterfaceAdapter.MapAdapter.MapController;
import InterfaceAdapter.MapAdapter.MapPresenter;
import InterfaceAdapter.PlayerAdapter.PlayerController;
import InterfaceAdapter.PlayerAdapter.PlayerPresenter;
import InterfaceAdapter.TileAdapter;
import InterfaceAdapter.TimeAdapter;
import InterfaceAdapter.UseCaseManager;

public class AdapterManager {
    private UseCaseManager useCaseManager;
    private PlayerPresenter playerPresenter;
    private PlayerController playerController;
    private MapPresenter mapPresenter;
    private MapController mapController;
    private TimeAdapter timeAdapter;
    private TileAdapter tileAdapter;
    private long mapSeed = 12345L;

    public AdapterManager(UseCaseManager useCaseManager) {
        this.useCaseManager = useCaseManager;
        playerPresenter = new PlayerPresenter(useCaseManager);
        playerController = new PlayerController(useCaseManager);
        mapPresenter = new MapPresenter(useCaseManager);
        mapController = new MapController(32, 32, MapType.ISLAND, mapSeed, useCaseManager);
        timeAdapter = new TimeAdapter(useCaseManager);
        tileAdapter = new TileAdapter(useCaseManager);
    }
    public UseCaseManager getUseCaseManager() {
        return useCaseManager;
    }
    public void setUseCaseManager(UseCaseManager useCaseManager) {
        this.useCaseManager = useCaseManager;
    }

    public PlayerPresenter getPlayerPresenter() {
        return playerPresenter;
    }

    public void setPlayerPresenter(PlayerPresenter playerPresenter) {
        this.playerPresenter = playerPresenter;
    }

    public PlayerController getPlayerController() {
        return playerController;
    }

    public void setPlayerController(PlayerController playerController) {
        this.playerController = playerController;
    }

    public MapPresenter getMapPresenter() {
        return mapPresenter;
    }

    public void setMapPresenter(MapPresenter mapPresenter) {
        this.mapPresenter = mapPresenter;
    }

    public MapController getMapController() {
        return mapController;
    }

    public void setMapController(MapController mapController) {
        this.mapController = mapController;
    }

    public TimeAdapter getTimeAdapter() {
        return timeAdapter;
    }

    public void setTimeAdapter(TimeAdapter timeAdapter) {
        this.timeAdapter = timeAdapter;
    }

    public TileAdapter getTileAdapter() {
        return tileAdapter;
    }

    public void setTileAdapter(TileAdapter tileAdapter) {
        this.tileAdapter = tileAdapter;
    }
}
